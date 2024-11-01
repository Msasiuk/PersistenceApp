$(document).ready(function() {
});

async function iniciarSesion() {
    // Obtener los valores de los campos
    let datos = {};
    datos.email = document.getElementById('txtEmail').value.trim();
    datos.password = document.getElementById('txtPassword').value.trim();
    
    // Verificar si algún campo está vacío
    if (!datos.email || !datos.password) {
        alert('Todos los campos son obligatorios');
        return;
    }

    try {
        // Realizar la solicitud de inicio de sesión
        const response = await fetch('api/login', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datos),
        });

        // Verificar si la respuesta es exitosa
        const respuesta = await response.text(); // Cambiado `request` a `response`
        
        if (respuesta != 'Fail') {
			localStorage.token = respuesta;
			localStorage.email = datos.email;
            window.location.href = 'usuarios.html'; // Cambiado `windows` a `window`
        } else {
            alert("Credenciales incorrectas");
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Hubo un problema con la solicitud');
        window.location.href = 'login.html';
    }
}