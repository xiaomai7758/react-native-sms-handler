
package com.xiaomai.sms;

import com.facebook.react.ReactPackage;  
import com.facebook.react.bridge.JavaScriptModule;  
import com.facebook.react.bridge.NativeModule;  
import com.facebook.react.bridge.ReactApplicationContext;  
import com.facebook.react.uimanager.ViewManager;  
  
import java.util.ArrayList;  
import java.util.Collections;  
import java.util.List;  
  
public class SmsHandlerPackage implements ReactPackage {  
  @Override  
  public List createNativeModules(ReactApplicationContext reactContext) {  
        List modules = new ArrayList<>();  
        modules.add(new SmsHandlerModule(reactContext));  
        return modules;  
  }  
  
 
  public List createJSModules() {  
        return Collections.emptyList();  
  }  
  
  @Override  
  public List createViewManagers(ReactApplicationContext reactContext) {  
        return Collections.emptyList();  
  }  
}
