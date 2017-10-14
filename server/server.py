from flask import Flask, request, jsonify
app = Flask(__name__)

performances = [
    {
        "id": 0,
        "title": "Nancy Juggling",
        "lat": 41.390176,
        "lng": 2.181766
    }
]

next_id = 1


@app.route("/")
def get_performances():
    print performances
    return jsonify(performances)


@app.route("/add", methods=["POST"])
def add_performance():
    print request
    if not request.json:
        abort(400)

    new = {
        "id": next_id,
        "title": request.json["title"],
        "lat":   request.json["lat"],
        "lng":   request.json["lng"]
    }
    next_id += 1
    performances.append(task)

    return jsonify(performances), 201


if __name__ == "__main__":
    app.run()
