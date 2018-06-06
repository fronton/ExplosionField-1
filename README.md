# 碎片动画

[![](https://jitpack.io/v/wenchaosong/ExplosionField.svg)](https://jitpack.io/#wenchaosong/ExplosionField)
[![Hex.pm](https://img.shields.io/github/stars/wenchaosong/ExplosionField.svg)](https://github.com/wenchaosong/ExplosionField)

在原项目[ExplosionField](https://github.com/tyrantgit/ExplosionField) 基础上
增加了动画结束的监听

## 效果图

![image](/image/explosionfield.gif )

## 使用步骤

#### Step 1

Gradle
```groovy
dependencies{
    compile 'com.github.wenchaosong:ExplosionField:1.0.0'
}
```
或者引用本地lib
```groovy
compile project(':Library')
```

#### Step 2
```xml
ExplosionField explosionField = ExplosionField.getInstance(this);
explosionField.explode(view);
```
