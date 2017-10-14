from flask import Flask, request, jsonify
import json, types
from constants import *

app = Flask(__name__)

@app.route("/")
def get_performances():
    return jsonify(performances)


@app.route("/categories")
def get_categories():
    return categories


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

    # Check id
    identifier = data_dict["id"]

    # Check categories
    if type(data_dict["categories"]) is not list:
        return "Error: categories must be an array, valid elements are: " + ", ".join(categories), 400

    provided_categories = data_dict["categories"]
    invalid_categories = [cat for cat in provided_categories if cat.lower() not in categories]

    if len(invalid_categories) > 0:
        return "Error: invalid categories provided: %s \nValid categories are: %s" % (", ".join(invalid_categories), ", ".join(categories)), 400

    # Check coordinates
    try:
        lat = float(data_dict["lat"])
        lng = float(data_dict["lng"])
    except:
        return "Error: count not parse either lat or lng, make sure these are int/float", 400

    new_performance = {
        "id":           identifier,
        "categories":   provided_categories,
        "lat":          lat,
        "lng":          lng
    }

    performances.append(new_performance)
    
    return "New performance added: %s" % str(new_performance), 201


if __name__ == "__main__":
    app.run()
