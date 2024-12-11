
package sgsa;



public class RuleData {
    
    private String rule_id;
    private String name_rule;
   private String content_rule;
    
    
    
    public RuleData(String rule_id, String name_rule, String content_rule){
        this.rule_id = rule_id;
        this.name_rule = name_rule;
        this.content_rule=content_rule;
        
    }
    
    
    public String getRule_id(){
        return rule_id;
    }
    public String getName_rule(){
        return name_rule;
    }
    public String getContent_rule(){
        return content_rule ;
    }
   
  
}
