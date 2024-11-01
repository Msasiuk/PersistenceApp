$(document).ready(function() {
	//registrarUsuario(); Lo va a llamar no lo va a inicializar por defecto
});

async function registrarUsuario() {
    // Obtener los valores de los campos
    let datos = {};
    datos.nombre = document.getElementById('txtNombre').value.trim();
    datos.apellido = document.getElementById('txtApellido').value.trim();
    datos.email = document.getElementById('txtEmail').value.trim();
    datos.telefono = document.getElementById('txtTelefono').value.trim();
    datos.password = document.getElementById('txtPassword').value.trim();
    
    let repetirPassword = document.getElementById('txtRepetirPassword').value.trim();
    
    // Verificar si algún campo está vacío
    if (!datos.nombre || !datos.apellido || !datos.email || !datos.telefono || !datos.password || !repetirPassword) {
        alert('Todos los campos son obligatorios');
        return;
    }

    // Validar que las contraseñas coincidan
    if (repetirPassword !== datos.password) {
        alert('Las contraseñas deben ser iguales');
        return;
    }

    try {
        // Realizar la solicitud para registrar el usuario
        const response = await fetch('api/usuarios', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datos),
        });

        // Verificar si la respuesta es exitosa
        if (response.ok) {  // response.ok es true si el estado es 200-299
            alert('Usuario registrado correctamente');
            window.location.href = 'login.html'; // Redirige a usuario.html
        } else {
            alert('Error al registrar el usuario');
            window.location.href = 'registrar.html'; // Redirige a registrar.html si hay error
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Hubo un problema con la solicitud');
        window.location.href = 'registrar.html'; // Redirige a registrar.html en caso de error de red
    }
}