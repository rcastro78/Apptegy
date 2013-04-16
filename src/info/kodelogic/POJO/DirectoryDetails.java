package info.kodelogic.POJO;

public class DirectoryDetails {
  private String address, department, email, faxNbr, fName, lName, note, phone, title,fullName;

  public DirectoryDetails(String fullName,String address, String department, String email,
                          String faxNbr, String fName, String lName, String note, String phone,
                          String title) {
    this.fullName=fullName;
	this.address = address;
    this.department = department;
    this.email = email;
    this.faxNbr = faxNbr;
    this.fName = fName;
    this.lName = lName;
    this.note = note;
    this.phone = phone;
    this.title = title;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  
  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFaxNbr() {
    return faxNbr;
  }

  public void setFaxNbr(String faxNbr) {
    this.faxNbr = faxNbr;
  }

  public String getfName() {
    return fName;
  }

  public void setfName(String fName) {
    this.fName = fName;
  }

  public String getlName() {
    return lName;
  }

  public void setlName(String lName) {
    this.lName = lName;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

public String getFullName() {
	return fullName;
}

public void setFullName(String fullName) {
	this.fullName = fullName;
}
  
}