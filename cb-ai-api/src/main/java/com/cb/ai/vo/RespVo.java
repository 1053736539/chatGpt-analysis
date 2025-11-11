package com.cb.ai.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Description:
 * @Author: ARPHS
 * @Date: 2024-10-25 16:18
 * @Version: 1.0
 **/
public interface RespVo {

    @Data
    class BaseVo<T>{
        @JSONField(name = "status_code")
        private Long statusCode;
        @JSONField(name = "status_message")
        private String statusMessage;
        @JSONField(name = "data")
        private T data;

        public boolean isOk(){
            return this.getStatusCode().equals(200L);
        }

        public String getDataText(){
            T data = this.getData();
            if(null != data){
                if(data instanceof DataVo){
                    return ((DataVo)data).getResult().getText();
                }
            }
            return null;
        }
    }

    @Data
    class UploadFileGetUrlRes{
        @JSONField(name = "flowId")
        private String flowId;
        @JSONField(name = "file_path")
        private String filePath;
        @JSONField(name = "relative_path")
        private String relativePath;
    }

    @Data
    class DataVo{
        @JSONField(name = "result")
        private ResultVo result;
        @JSONField(name = "session_id")
        private String sessionID;
        @JSONField(name = "backend")
        private String backend;
    }

    @Data
    class ResultVo{
        @JSONField(name = "text")
        private String text;
        @JSONField(name = "source")
        private Long source;
        @JSONField(name = "message_id")
        private Long messageID;
        @JSONField(name = "answer")
        private String answer;
    }

}
