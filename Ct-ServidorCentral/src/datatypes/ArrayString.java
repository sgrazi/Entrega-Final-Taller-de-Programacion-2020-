package datatypes;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ArrayString {

	private String[] strings;
	
	public ArrayString(int size){
		this.strings = new String[size];
	}

	public String[] getStrings() {
		return this.strings;
	}

	public void setStrings(String[] col) {
		this.strings = col;
	}
	
}
