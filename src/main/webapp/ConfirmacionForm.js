document.addEventListener("DOMContentLoaded", ()=>{
	const formularios = document.querySelectorAll('.form-confirm');
	
	formularios.forEach(form =>{
		const mensaje = form.dataset.mensaje || '¿Estás seguro?';
		
		form.addEventListener("submit",(e)=>{
			const confirmar = confirm(mensaje);
			
			if(!confirmar){
				e.preventDefault();
			}
		})
	})
})