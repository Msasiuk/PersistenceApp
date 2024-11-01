$(document).ready(function() {
	// Cargar la lista de usuarios y activar DataTables
	cargarUsuarios();
  $('#usuarios').DataTable();
  actualizarEmailDelUsuario();
});

async function cargarUsuarios() {
	// rawResponse se utiliza para almacenar la respuesta de la solicitud GET
	const rawResponse = await fetch('api/usuarios', { 
		method: 'GET',
		headers: getHeaders()
	});
	
	// Convierte la respuesta a JSON para obtener la lista de usuarios
	const usuarios = await rawResponse.json();
		
	let listadoHtml = '';
		
	for(let usuario of usuarios){
		let botonEliminar = '<a href="#" onclick="eliminarUsuario(' + usuario.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
			
		let usuarioHtml = '<tr><td>' + usuario.id + '</td><td>' + usuario.nombre + ' ' + usuario.apellido + '</td><td>'
		+ usuario.email + '</td><td>'  + usuario.telefono + '</td><td>' + botonEliminar + '</td></tr>';
		listadoHtml += usuarioHtml;
	}
	
	// Inserta el HTML generado en el tbody de la tabla
	document.querySelector('#usuarios tbody').outerHTML = listadoHtml;
}

function getHeaders() {
	return{	'Accept': 'application/json',
	        'Content-Type': 'application/json',
			'Authorization': 'localStorage.token'
			};
}
		
async function eliminarUsuario(id){
    if(!confirm('Desea eliminar usuario?')){  
        return;  // Si el usuario cancela, no continúa con la eliminación
    }

	// Realiza una solicitud DELETE para eliminar el usuario
    await fetch('api/usuarios/' + id, { 
        method: 'DELETE',
        headers: getHeaders()
			
    });

	// location.reload(); // Al terminar la funcion recarga el html con los cambios realizados
    // Elimina el usuario de la tabla sin recargar la página
    cargarUsuarios(); // Vuelve a cargar los usuarios en la tabla
}

function actualizarEmailDelUsuario(){
	document.getElementById("txt-email-usuario").outerHTML = localStorage.email;
};