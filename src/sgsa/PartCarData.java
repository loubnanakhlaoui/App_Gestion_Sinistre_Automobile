
package sgsa;


public class PartCarData {
    
    private String part_id;
    private String part_name;
    private String supplier_name;
    private String supplier_phone;
    private String imageP;
    
    
    public PartCarData(String part_id, String part_name, String supplier_name, String supplier_phone, String imageP){
        this.part_id = part_id;
        this.part_name = part_name;
        this.supplier_name = supplier_name;
        this.supplier_phone = supplier_phone;
        this.imageP = imageP;
    }
    
    
    public String getPart_id(){
        return part_id;
    }
    public String getPart_name(){
        return part_name;
    }
    public String getSupplier_name (){
        return supplier_name ;
    }
   
    public String getSupplier_phone(){
        return supplier_phone;
    }
     public String getImageP(){
        return imageP;
    }
}
