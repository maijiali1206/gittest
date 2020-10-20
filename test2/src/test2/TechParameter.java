package test2;
 
public class TechParameter {
	String key;
	String value;
	String isSubstantive; //是否实质性参数  Y--是   N--否
	//String isImportant; //是否重要参数  Y--重要  G--一般  N--不区分
	
	public TechParameter(String key, String value, String isSubstantive) {
		this.key = key;
		this.value = value;
		this.isSubstantive = isSubstantive;
		//this.isImportant = isImportant;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getIsSubstantive() {
		return isSubstantive;
	}
	public void setIsSubstantive(String isSubstantive) {
		this.isSubstantive = isSubstantive;
	}
	/*public String getIsImportant() {
		return isImportant;
	}
	public void setIsImportant(String isImportant) {
		this.isImportant = isImportant;
	}*/
	public String toString() {
		return key+":"+value+":"+isSubstantive;
	}
	
}
