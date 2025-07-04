package io.appium.uiautomator2.model.api;

import java.util.List;
import java.util.Map;

public class SourceModel extends BaseModel {

    public  List<String> enableAttrs;

    public SourceModel() {}

    public SourceModel(
            List<String> enableAttrs
    ) {

        this.enableAttrs = enableAttrs;
    }
}
