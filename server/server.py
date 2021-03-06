from flask import Flask, request, jsonify
import json, types
import requests
from constants import *

app = Flask(__name__)

@app.route("/")
def get_performances():
    return json.dumps(state)


'''@app.route("/categories")
def get_categories():
    return categories'''


@app.route("/add", methods=["POST"])
def add_performance():
    try:
        data_dict = json.loads(request.get_data())
    except:
        return "Error: could not parse JSON, make sure it is not malformed", 400
    
    provided_fields = data_dict.keys()
    missing_fields = [field for field in required_fields if field not in provided_fields]
    
    if len(missing_fields) > 0:
        return "Error: missing fields: " + ", ".join(missing_fields), 400

    # Check id - Todo?
    identifier = data_dict["id"]

    provided_category = data_dict["category"]

    # Check categories
    '''if type(data_dict["categories"]) is not list:
        return "Error: categories must be an array, valid elements are: " + ", ".join(categories), 400

    provided_categories = data_dict["categories"]
    invalid_categories = [cat for cat in provided_categories if cat.lower() not in categories]

    if len(invalid_categories) > 0:
        return "Error: invalid categories provided: %s \nValid categories are: %s" % (", ".join(invalid_categories), ", ".join(categories)), 400'''

    # Check coordinates
    try:
        lat = float(data_dict["lat"])
        lng = float(data_dict["lng"])
    except:
        return "Error: count not parse either lat or lng, make sure these are int/float", 400

    new_performance = {
        "id":           identifier,
        "category":     provided_category,
        "lat":          lat,
        "lng":          lng
    }

    try_add_new_field(data_dict, new_performance, "description")
    try_add_new_field(data_dict, new_performance, "praises")

    state["performances"].append(new_performance)

    poke_firebase(new_performance)
    
    return json.dumps({}), 201

def try_add_new_field(source, dest, field):
	try:
		dest[field] = source[field]
	except:
		pass

def poke_firebase(new_performance):
    url = "https://fcm.googleapis.com/fcm/send"
    headers = {
        "Content-Type": "application/json",
        "Authorization": "key=AIzaSyA9eQ8wiu3DPGjcdEzk3AI9qopk9b6hv2M"
    }
    body = {
        "to": "/topics/updates",
        "data": {
            "type": "new_performance",
            "data": json.dumps(new_performance)
        }
    }
    print body
    
    response = requests.post(url, headers=headers, data=json.dumps(body))
    

@app.route("/praise", methods=["POST"])
def praise():
    try:
        data_dict = json.loads(request.get_data())
        identifier = data_dict["id"]
    except:
        return "Error: performance id not provided", 400

    target_performance = [p for p in state["performances"] if p["id"] == identifier]
    
    if len(target_performance) < 1:
        return "Error: could not find performance by id"

    target_performance[0]["praises"] += 1

    return json.dumps({"message": "Performance praised"}), 200

@app.route("/whipe", methods=["GET"])
def whipe():
    global state
    state["performances"] = []

    return json.dumps({}), 200


if __name__ == "__main__":
    app.run(host="0.0.0.0", debug="true", port=5000)
