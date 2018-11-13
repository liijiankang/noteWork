#### 获取Windows上apache服务提供的文件
```aidl
    #!/bin/bash
    path=`pwd`
    processor=$path/hdi-processor-nar-0.0.1.nar
    service=$path/hdi-services-nar-0.0.1.nar
    bundle=$path/hdi-bundle-nar-0.0.1.nar
    
    
    #if [  -f "$processor" ]; then
    #       echo "
    #******************** update processor **********************
    #       "
    #       rm -rf $processor
    #       wget http://10.0.7.196:8080/hdi-processor/hdi-processor-nar/target/hdi-processor-nar-0.0.1.nar
    #else 
    #       echo "
    #******************** download processor **********************
    #        "
    #       wget http://10.0.7.196:8080/hdi-processor/hdi-processor-nar/target/hdi-processor-nar-0.0.1.nar
    #fi
    #
    #if [  -f "$service" ]; then
    #       echo "
    #******************* update service *********************
    #       "
    #       rm -rf $service
    #       wget http://10.0.7.196:8080/hdi-services/hdi-services-nar/target/hdi-services-nar-0.0.1.nar
    #else
    #       echo "
    #******************* download service **********************
    #        "
    #       wget http://10.0.7.196:8080/hdi-services/hdi-services-nar/target/hdi-services-nar-0.0.1.nar
    #fi
    #
    #echo "
    #********************* service restart nifi *************************
    #"
    
    if [  -f "$bundle" ]; then
           echo "
    ******************** update bundle **********************
           "
           rm -rf $bundle
           wget http://10.0.7.196:8080/hdi-bundle-nar/target/hdi-bundle-nar-0.0.1.nar
    else
           echo "
    ******************** download processor **********************
            "
           wget http://10.0.7.196:8080/hdi-bundle-nar/target/hdi-bundle-nar-0.0.1.nar
    fi

```