package objects.attributes;

import lombok.Data;

import java.util.List;

@Data
public abstract class Attribute {

    private String name;
    private List<String> rules;

}
