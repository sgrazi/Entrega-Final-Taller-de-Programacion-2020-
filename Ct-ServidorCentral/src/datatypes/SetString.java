package datatypes;
import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class SetString {

	private Set<String> strings;
	
	public SetString(){
		this.strings = new HashSet<>();
	}

	public Set<String> getStrings() {
		return this.strings;
	}

	public void setStrings(Set<String> col) {
		this.strings = col;
	}
	

}
