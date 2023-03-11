from flask import Flask, jsonify, request


app = Flask(__name__)

@app.route('/', methods=['POST'])
def upload_image():
    if 'image' in request.files:
        image = request.files['image']
        # Procesar la imagen aquí (por ejemplo, guardarla en disco)
        return 'Imagen recibida correctamente'
    else:
        return 'No se envió ninguna imagen'

@app.route('/coordenada', methods=['POST'])
def coordenada():
    data = request.get_json()
    x = data['x']
    y = data['y']
    mensaje = 'Coordenadas: x={}, y={}'.format(x, y)
    print('Coordenadas: x={}, y={}'.format(x, y))
    return mensaje

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')
