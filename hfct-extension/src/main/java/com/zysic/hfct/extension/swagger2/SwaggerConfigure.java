package com.zysic.hfct.extension.swagger2;

import com.zysic.hfct.core.errcode.BusinessErrorCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**

 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfigure {

    private static List<ResponseMessage> responseMessageList(){
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(BusinessErrorCode.SUCCESS.getErrcode()).message(BusinessErrorCode.SUCCESS.getErrmsg()).build());
        responseMessageList.add(new ResponseMessageBuilder().code(BusinessErrorCode.UNKNOWN.getErrcode()).message(BusinessErrorCode.UNKNOWN.getErrmsg()).build());
        return responseMessageList;
    }

    @Bean
    @ConditionalOnMissingBean
    public Docket createRestApi(){
        ParameterBuilder headerBuilder = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();
        headerBuilder.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        parameters.add(headerBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                .globalResponseMessage(RequestMethod.GET,responseMessageList())
                .globalResponseMessage(RequestMethod.POST,responseMessageList())
                .globalResponseMessage(RequestMethod.PUT,responseMessageList())
                .globalResponseMessage(RequestMethod.DELETE,responseMessageList())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters);

    }

    /**
     * 构建ApiInfo对象
     * @return
     */
    private ApiInfo buildApiInfo(){
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("HFCT接口文档")
                .description("HFCT")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
        return apiInfo;
    }
}
