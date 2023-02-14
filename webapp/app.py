from flask import Flask, jsonify

app = Flask(__name__)

@app.route('/')
def index():
    imagenes=[ {
                "nombre": "img1",
                "descripcion": "Es un gato"
                },
               {
                "nombre":"img2",
                "descripcion": "nada"
               }
    ]
    return jsonify(imagenes)

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')
