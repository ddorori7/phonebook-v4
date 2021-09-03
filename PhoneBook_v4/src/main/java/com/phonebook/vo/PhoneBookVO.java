package com.phonebook.vo;

public class PhoneBookVO {

	// 필드 VO 객체에서는 기본타입 대신 Wrapper 사용
	private Long id;
	private String name;
	private String hp;
	private String tel;

	// 생성자 - 기본 생성자가 필요
	public PhoneBookVO() {}
	
	public PhoneBookVO(String name, String hp) {
		this.name = name;
		this.hp = hp;
	}

	public PhoneBookVO(String name, String hp, String tel) {
		this(name, hp);
		this.tel = tel;	
	}

	public PhoneBookVO(Long id, String name, String hp, String tel) {
		this(name, hp, tel);
		this.id = id;	
	}

	
	
	// getter, setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	// toString
	@Override
	public String toString() {
		return "PhoneBookVO [id=" + id + ", name=" + name + ", hp=" + hp + ", tel=" + tel + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
