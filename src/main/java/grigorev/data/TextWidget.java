package grigorev.data;

import lombok.Data;

@Data
public class TextWidget extends Widget {

    private String text;
    private String font;
    private Integer fontSize;

    public WidgetType getWidgetType() {
        return WidgetType.TEXT;
    }

}
