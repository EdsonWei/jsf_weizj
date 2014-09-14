package onlinebook;

import java.io.Serializable;

public class pk implements Serializable{
	
		public pk() { 
		}
		public pk(String name, String id) {
			this.isbn = name;
			this.id = id;
		} 
		public String getIsbn() {
			return isbn;
		}
		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}
		
		private String isbn; 
		private String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		@Override
		public int hashCode() {
			final int PRIME = 31;
			int result = 1;
			result = PRIME * result + ((isbn == null) ? 0 : isbn.hashCode());
			result = PRIME * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
		  if (this == obj)
		    return true;
		  if (obj == null)
		    return false;
		  if (getClass() != obj.getClass())
		    return false;
		  final pk other = (pk) obj;
		  if (isbn == null) {
				if (other.isbn != null)
					return false;
			} else if (!isbn.equals(other.isbn))
					return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
					return false;
		     return true;
		} 

}




