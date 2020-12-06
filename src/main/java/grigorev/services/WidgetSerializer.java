package grigorev.services;

import grigorev.data.WidgetType;

public interface WidgetSerializer {

    String serialize();

    WidgetType getWidgetType();

}
