package belatrix;


 class Producto {  
	  private String descripcion;  
	  private String precio;  
	  
		public Producto() {
		}
		
		public Producto(String descripcion, String precio) {
			this.descripcion = descripcion;
			this.precio = precio;
		}
	  
		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public String getPrecio() {
			return precio;
		}

		public void setPrecio(String precio) {
			this.precio = precio;
		}

		@Override
		public String toString() {
			return this.getDescripcion() + "  -  " + this.getPrecio();
		} 
} 

