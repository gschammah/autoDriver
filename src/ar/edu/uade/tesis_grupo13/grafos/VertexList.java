package ar.edu.uade.tesis_grupo13.grafos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class VertexList extends ArrayList<Coordenada>{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {		
		StringBuilder str = new StringBuilder();		
		
		//remuevo duplicados
		HashSet<Coordenada> h = new HashSet<Coordenada>(this);
		Iterator<Coordenada> itr = h.iterator();
		
		while (itr.hasNext()) {
			Coordenada coord = itr.next();
			str.append(coord.getMatrizX() + ", " + coord.getMatrizY() + "\n");			
		}
		
		return str.toString();
	}

}
