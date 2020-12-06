package grigorev.data;

import lombok.Data;

@Data
public class PictureWidget extends Widget {

    private String url;

    public WidgetType getWidgetType() {
        return WidgetType.PICTURE;
    }

}
