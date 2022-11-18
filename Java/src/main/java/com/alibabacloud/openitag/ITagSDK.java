// This file is auto-generated, don't edit it. Thanks.
package com.alibabacloud.openitag;

import com.aliyun.tea.*;

public class ITagSDK extends com.aliyun.openitag20220616.Client {

    public String _bucNo;
    public ITagSDK(com.aliyun.teaopenapi.models.Config config, String bucNo) throws Exception {
        super(config);
        this._endpoint = this.formatEndpoint(_endpoint);
        this._bucNo = bucNo;
    }


    public String formatEndpoint(String endpoint) throws Exception {
        java.util.List<String> urlArr = com.aliyun.darabonbastring.Client.split(endpoint, "//", null);
        Long maxIndex = com.aliyun.darabonbanumber.Client.sub(com.aliyun.darabonba.array.Client.size(urlArr), 1);
        return com.aliyun.darabonba.array.Client.get(urlArr, maxIndex);
    }

    public String buildToken(String accessKeyId, String accessKeySecret, String budId, String reqId, String ts) throws Exception {
        String line = "" + accessKeyId + "_" + budId + "_" + reqId + "_" + ts + "_tiansuo";
        byte[] signature = com.alibabacloud.rsautil.Client.shaSign(com.aliyun.teautil.Common.toBytes(line), accessKeySecret);
        byte[] sign = com.alibabacloud.rsautil.Client.bEncode(signature);
        return com.aliyun.teautil.Common.toString(sign);
    }

    /**
     * 构建集团域的请求头
     */
    public com.aliyun.teaopenapi.models.OpenApiRequest buildHeaders(com.aliyun.teaopenapi.models.OpenApiRequest request) throws Exception {
        String accessKeyId = this.getAccessKeyId();
        String accessKeySecret = this.getAccessKeySecret();
        String reqTimestamp = com.alibabacloud.rsautil.Client.genTs();
        reqTimestamp = "" + reqTimestamp + "";
        String reqId = com.alibabacloud.rsautil.Client.uuid();
        String buNo = _bucNo;
        String sign = this.buildToken(accessKeyId, accessKeySecret, buNo, reqId, reqTimestamp);
        request.headers = TeaConverter.merge(String.class,
            TeaConverter.buildMap(
                new TeaPair("bucAccountNo", buNo),
                new TeaPair("alphaqTntInstId", accessKeyId),
                new TeaPair("referer", _endpoint),
                new TeaPair("reqTimestamp", reqTimestamp),
                new TeaPair("reqId", reqId),
                new TeaPair("Content-type", "Application/json;charset=UTF-8"),
                new TeaPair("reqSourceApp", "tiansuo"),
                new TeaPair("token", sign)
            ),
            request.headers
        );
        return request;
    }

    public java.util.Map<String, ?> callApi(com.aliyun.teaopenapi.models.Params params, com.aliyun.teaopenapi.models.OpenApiRequest request, com.aliyun.teautil.models.RuntimeOptions runtime) throws Exception {
        if (com.aliyun.teautil.Common.isUnset(TeaModel.buildMap(params))) {
            throw new TeaException(TeaConverter.buildMap(
                new TeaPair("code", "ParameterMissing"),
                new TeaPair("message", "'params' can not be unset")
            ));
        }

        request = this.buildHeaders(request);
        if (com.aliyun.teautil.Common.isUnset(_signatureAlgorithm) || !com.aliyun.teautil.Common.equalString(_signatureAlgorithm, "v2")) {
            return this.doRequest(params, request, runtime);
        } else if (com.aliyun.teautil.Common.equalString(params.style, "ROA") && com.aliyun.teautil.Common.equalString(params.reqBodyType, "json")) {
            return this.doROARequest(params.action, params.version, params.protocol, params.method, params.authType, params.pathname, params.bodyType, request, runtime);
        } else if (com.aliyun.teautil.Common.equalString(params.style, "ROA")) {
            return this.doROARequestWithForm(params.action, params.version, params.protocol, params.method, params.authType, params.pathname, params.bodyType, request, runtime);
        } else {
            return this.doRPCRequest(params.action, params.version, params.protocol, params.method, params.authType, params.bodyType, request, runtime);
        }

    }
}
