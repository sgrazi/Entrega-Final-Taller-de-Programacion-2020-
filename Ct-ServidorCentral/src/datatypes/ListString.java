package datatypes;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ListString {

	private List<String> strings;
	
	public ListString(){
		this.strings = new ArrayList<String>();
	}

	public List<String> getStrings() {
		return this.strings;
	}

	public void setStrings(List<String> col) {
		this.strings = col;
	}
	

}
