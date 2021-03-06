
public class Vertex {
	private String label = "";
	private int weight = 0;
	private String prevNode = "";
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Vertex(String label) {
		this.label = label;
	}
	public Vertex(String label, int weight, String prevNode) {
		this.label = label;
		this.weight = weight;
		this.prevNode = prevNode;
	}
	public Vertex() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
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
		Vertex other = (Vertex) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getPrevNode() {
		return prevNode;
	}
	public void setPrevNode(String prevNode) {
		this.prevNode = prevNode;
	}
}
