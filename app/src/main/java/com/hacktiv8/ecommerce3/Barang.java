package com.hacktiv8.ecommerce3;

public class Barang {
    String mKategoriBarang, mNamaBarang, mBrand, mJumlahBarang, mHargaBarang;

    Barang(){

    }
    public Barang(String mKategoriBarang, String mNamaBarang, String mBrand, String mJumlahBarang, String mHargaBarang) {
        this.mKategoriBarang = mKategoriBarang;
        this.mNamaBarang = mNamaBarang;
        this.mBrand = mBrand;
        this.mJumlahBarang = mJumlahBarang;
        this.mHargaBarang = mHargaBarang;
    }
    public String getmKategoriBarang() {
        return mKategoriBarang;
    }

    public void setmKategoriBarang(String mKategoriBarang) {
        this.mKategoriBarang = mKategoriBarang;
    }

    public String getmNamaBarang() {
        return mNamaBarang;
    }

    public void setmNamaBarang(String mNamaBarang) {
        this.mNamaBarang = mNamaBarang;
    }

    public String getmBrand() {
        return mBrand;
    }

    public void setmBrand(String mBrand) {
        this.mBrand = mBrand;
    }

    public String getmJumlahBarang() {
        return mJumlahBarang;
    }

    public void setmJumlahBarang(String mJumlahBarang) {
        this.mJumlahBarang = mJumlahBarang;
    }

    public String getmHargaBarang() {
        return mHargaBarang;
    }

    public void setmHargaBarang(String mHargaBarang) {
        this.mHargaBarang = mHargaBarang;
    }
}
