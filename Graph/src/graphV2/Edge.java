package graphV2;

public class Edge<T> {
	private T nodeStart;
	private T nodeEnd;
	
	private Edge(T nodeStart, T nodeEnd) {
		this.nodeStart = nodeStart;
		this.nodeEnd = nodeEnd;
	
	}
	
	public String toString() {
		return "(" + nodeStart + ", " + nodeEnd + ")";
	}

	public T getNodeStart() {
		return nodeStart;
	}

	public T getNodeEnd() {
		return nodeEnd;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nodeEnd == null) ? 0 : nodeEnd.hashCode());
		result = prime * result + ((nodeStart == null) ? 0 : nodeStart.hashCode());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge<T> other = (Edge<T>) obj;
		if (nodeEnd == null) {
			if (other.nodeEnd != null)
				return false;
		} else if (!nodeEnd.equals(other.nodeEnd))
			return false;
		if (nodeStart == null) {
			if (other.nodeStart != null)
				return false;
		} else if (!nodeStart.equals(other.nodeStart))
			return false;
		return true;
	}

	public static<T> Edge<T> createInstance(T nodeStart, T nodeEnd) {
		return new Edge<T>(nodeStart, nodeEnd);
	}

	
}
