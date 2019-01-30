
# react-native-sms-handler
环境: `2018-01-31`, `MacOS 10.13.6`, `API 23/27/18 Android8.0`, `Android Studio 3.2.1`, `华为EMUI 4.1 Android6.0`
> git中rn的短信相关组件太糟糕了,自己写一个
## 安装
`$ npm install react-native-sms-handler --save`
### 在项目根目录link (`npm link pathtoproject 开发神器,可以在本地测试要发布的npm包`)
`$ react-native link react-native-sms-handler`

### Android配置(`一般前三点没问题自动加,第四条手动?`)

1. `android/app/src/main/java/[...]/MainActivity.java`
   - 文件顶部添加引用: `import com.xiaomai.sms.RNSmsPackage;`
   - getPackages()返回列表添加 `new RNSmsPackage()`
2. `android/settings.gradle`: 添加
  	```java
  	include ':react-native-sms-handler'
  	project(':react-native-sms-handler').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-sms-handler/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-sms-handler')
  	```
4. android/app/src/main/AndroidManifest.xml 添加权限
	 ```xml
	 <uses-permission android:name="android.permission.SEND_SMS"/>
	 <uses-permission android:name="android.permission.READ_SMS"/>
	 ```

## js页面中使用
```javascript
import SmsHandler from 'react-native-sms-handler';
import { PermissionsAndroid } from 'react-native';

// 一定要验证权限... 建议在 ComponentDidMount里做
PermissionsAndroid.request(PermissionsAndroid.PERMISSIONS.READ_SMS).then(result=>{
	if(PermissionsAndroid.RESULTS.GRANTED == result) {
		SmsHandler.getSmsInPhone(results => {
			console.log(results);
		});
	}
})
```

TODO: callback里返回 list 和 err 这样程序就不会崩溃里
  