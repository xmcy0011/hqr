# gen

## 生成

```shell
$ keytool -genkey -v -alias android_mip -keyalg RSA -keysize 2048 -validity 36500 -keystore android_mip.keystore

Enter keystore password:  # 输入证书文件密码，输入完成回车  
Re-enter new password:    # 再次输入证书文件密码，输入完成回车  
What is your first and last name?  
  [Unknown]:  # 输入名字和姓氏，输入完成回车  
What is the name of your organizational unit?  
  [Unknown]:  # 输入组织单位名称，输入完成回车  
What is the name of your organization?  
  [Unknown]:  # 输入组织名称，输入完成回车  
What is the name of your City or Locality?  
  [Unknown]:  # 输入城市或区域名称，输入完成回车  
What is the name of your State or Province?  
  [Unknown]:  # 输入省/市/自治区名称，输入完成回车  
What is the two-letter country code for this unit?  
  [Unknown]:  # 输入国家/地区代号（两个字母），中国为CN，输入完成回车  
Is CN=XX, OU=XX, O=XX, L=XX, ST=XX, C=XX correct?  
  [no]:       # 确认上面输入的内容是否正确，输入y，回车 
```

- MIP2.0
- 后勤工作部
- 国网上海电力公司
- 上海
- 上海
- 中国

## 迁移到标准格式

```shell
$ keytool -importkeystore -srckeystore android_mip.keystore -destkeystore android_mip.keystore -deststoretype pkcs12
```

## 查看

1. 查看key store
```shell
keytool -list -v -keystore hqr/app/sign/android_mip.jks
```

2. 查看apk解压后
```shell
keytool -printcert -file META-INF/CERT.RSA
```