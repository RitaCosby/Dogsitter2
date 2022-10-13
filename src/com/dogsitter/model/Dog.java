package com.dogsitter.model;

public class Dog {
	private Integer id;
	private String name;
	private String breed;
	private String size;
	private boolean barkCollar;
	private String foodType;
	private String feedingAmount;
	private String sleepingPreference;
	private String medicineNotes;
	private String dogFoodBrand;
	private String grooming;
	private int offLeash = 0; //default to null
	private int waterOk = 0;
	private int walk = 0;
	private int treats = 0;
	private int perDay = 0;
	
	public int getTreats() {
		return treats;
	}
	public void setTreats(int treats) {
		this.treats = treats;
	}
	public int getPerDay() {
		return perDay;
	}
	public void setPerDay(int perDay) {
		this.perDay = perDay;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public boolean isBarkCollar() {
		return barkCollar;
	}
	public void setBarkCollar(boolean barkCollar) {
		this.barkCollar = barkCollar;
	}
	public String getFoodType() {
		return foodType;
	}
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}
	public String getDogFoodBrand() {
		return dogFoodBrand;
	}
	public void setDogFoodBrand(String dogFoodBrand) {
		this.dogFoodBrand = dogFoodBrand;
	}
	public String getGrooming() {
		return grooming;
	}
	public void setGrooming(String grooming) {
		this.grooming = grooming;
	}
	
	public int getOffLeash() {
		return offLeash;
	}
	public void setOffLeash(int offLeash) {
		this.offLeash = offLeash;
	}
	public int getWaterOk() {
		return waterOk;
	}
	public void setWaterOk(int waterOk) {
		this.waterOk = waterOk;
	}
	public int getWalk() {
		return walk;
	}
	public void setWalk(int walk) {
		this.walk = walk;
	}
	public String getFeedingAmount() {
		return feedingAmount;
	}
	public void setFeedingAmount(String feedingAmount) {
		this.feedingAmount = feedingAmount;
	}
	public String getSleepingPreference() {
		return sleepingPreference;
	}
	public void setSleepingPreference(String sleepingPreference) {
		this.sleepingPreference = sleepingPreference;
	}
	public String getMedicineNotes() {
		return medicineNotes;
	}
	public void setMedicineNotes(String medicineNotes) {
		this.medicineNotes = medicineNotes;
	}
	
	
	
	
	
	
	

}
