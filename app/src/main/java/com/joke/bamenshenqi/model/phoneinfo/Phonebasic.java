package com.joke.bamenshenqi.model.phoneinfo;


public class Phonebasic {
    private Integer baseid;
    private String board;
    private String bootloader;
    private String brand;
    private String codename;
    private String device;
    private String display;
    private String incremental;
    private String manufacturer;
    private String model;
    private String product;
    private String releaze;
    private String sdk;
    private String sdkint;
    private String serial;

    public Phonebasic() {
        super();
    }

    public Integer getBaseid() {
        return this.baseid;
    }

    public String getBoard() {
        return this.board;
    }

    public String getBootloader() {
        return this.bootloader;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getCodename() {
        return this.codename;
    }

    public String getDevice() {
        return this.device;
    }

    public String getDisplay() {
        return this.display;
    }

    public String getIncremental() {
        return this.incremental;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public String getModel() {
        return this.model;
    }

    public String getProduct() {
        return this.product;
    }

    public String getReleaze() {
        return this.releaze;
    }

    public String getSdk() {
        return this.sdk;
    }

    public String getSdkint() {
        return this.sdkint;
    }

    public String getSerial() {
        return this.serial;
    }

    public void setBaseid(Integer baseid) {
        this.baseid = baseid;
    }

    public void setBoard(String board) {
        String v0 = board == null ? null : board.trim();
        this.board = v0;
    }

    public void setBootloader(String bootloader) {
        String v0 = bootloader == null ? null : bootloader.trim();
        this.bootloader = v0;
    }

    public void setBrand(String brand) {
        String v0 = brand == null ? null : brand.trim();
        this.brand = v0;
    }

    public void setCodename(String codename) {
        String v0 = codename == null ? null : codename.trim();
        this.codename = v0;
    }

    public void setDevice(String device) {
        String v0 = device == null ? null : device.trim();
        this.device = v0;
    }

    public void setDisplay(String display) {
        String v0 = display == null ? null : display.trim();
        this.display = v0;
    }

    public void setIncremental(String incremental) {
        String v0 = incremental == null ? null : incremental.trim();
        this.incremental = v0;
    }

    public void setManufacturer(String manufacturer) {
        String v0 = manufacturer == null ? null : manufacturer.trim();
        this.manufacturer = v0;
    }

    public void setModel(String model) {
        String v0 = model == null ? null : model.trim();
        this.model = v0;
    }

    public void setProduct(String product) {
        String v0 = product == null ? null : product.trim();
        this.product = v0;
    }

    public void setReleaze(String releaze) {
        String v0 = releaze == null ? null : releaze.trim();
        this.releaze = v0;
    }

    public void setSdk(String sdk) {
        String v0 = sdk == null ? null : sdk.trim();
        this.sdk = v0;
    }

    public void setSdkint(String sdkint) {
        String v0 = sdkint == null ? null : sdkint.trim();
        this.sdkint = v0;
    }

    public void setSerial(String serial) {
        String v0 = serial == null ? null : serial.trim();
        this.serial = v0;
    }
}

