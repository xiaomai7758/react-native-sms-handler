
# react-native-sms

## Getting started

`$ npm install react-native-sms --save`

### Mostly automatic installation

`$ react-native link react-native-sms`

### Manual installation


#### Android配置 一般前三点没问题自动加但

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.xiaomai.sms.RNSmsPackage;` to the imports at the top of the file
  - Add `new RNSmsPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-sms'
  	project(':react-native-sms').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-sms/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-sms')
  	```
4. android/app/src/main/AndroidManifest.xml 添加权限
```
<uses-permission android:name="android.permission.SEND_SMS"/>
<uses-permission android:name="android.permission.READ_SMS"/>
```

## Usage
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
  