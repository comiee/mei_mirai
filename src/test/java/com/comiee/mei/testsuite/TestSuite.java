package com.comiee.mei.testsuite;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.comiee.mei.testcase") // 指定要运行的包路径
public class TestSuite {
    // 这个类不需要任何代码
}


