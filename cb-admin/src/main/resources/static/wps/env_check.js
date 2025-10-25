function startWps(req, t, callback) {
	function startWpsInnder(reqInner, tryCount, bPop) {
		if (tryCount < 1) {
			if (callback)
				callback({
					status: 2,
					message: "请确认有没有正确的安装WPS2019，并允许浏览器打开WPS Office"
				});
			return;
		}
		var bRetry = true;
		var xmlReq = getHttpObj();
		//WPS客户端提供的接收参数的本地服务，HTTP服务端口为58890，HTTPS服务端口为58891
		//这俩配置，取一即可，不可同时启用
		xmlReq.open(reqInner.type, reqInner.url);
		xmlReq.onload = function (res) {
			bFinished = true;
			if (callback)
				callback({
					status: 0,
					res: res
				});
		}
		xmlReq.ontimeout = xmlReq.onerror = function (res) {
			xmlReq.bTimeout = true;
			if (bPop) { //打开wps并传参
				window.location.href = "ksoWPSCloudSvr://start=RelayHttpServer" //是否启动wps弹框
			}
			setTimeout(function () {
				if (bRetry) {
					bRetry = false;
					startWpsInnder(reqInner, --tryCount, false);
				}
			}, 1000);
		}
		if (IEVersion() < 10) {
			xmlReq.onreadystatechange = function () {
				if (xmlReq.readyState != 4)
					return;
				if (xmlReq.bTimeout) {
					return;
				}
				if (xmlReq.status === 200)
					xmlReq.onload();
				else
					xmlReq.onerror();
			}
		}
		xmlReq.timeout = 3000;
		xmlReq.send(t)
	}
	startWpsInnder(req, 4, true);
	return;
}

function getHttpObj() {
	var httpobj = null;
	if (IEVersion() < 10) {
		try {
			httpobj = new XDomainRequest();
		} catch (e1) {
			httpobj = new createXHR();
		}
	} else {
		httpobj = new createXHR();
	}
	return httpobj;
}
//兼容IE低版本的创建xmlhttprequest对象的方法
function createXHR() {
	if (typeof XMLHttpRequest != 'undefined') { //兼容高版本浏览器
		return new XMLHttpRequest();
	} else if (typeof ActiveXObject != 'undefined') { //IE6 采用 ActiveXObject， 兼容IE6
		var versions = [ //由于MSXML库有3个版本，因此都要考虑
			'MSXML2.XMLHttp.6.0',
			'MSXML2.XMLHttp.3.0',
			'MSXML2.XMLHttp'
		];

		for (var i = 0; i < versions.length; i++) {
			try {
				return new ActiveXObject(versions[i]);
			} catch (e) {
				//跳过
			}
		}
	} else {
		throw new Error('您的浏览器不支持XHR对象');
	}
}

function FormatSendData(data) {
	var strData = JSON.stringify(data);
	if (IEVersion() < 10)
		eval("strData = '" + JSON.stringify(strData) + "';");
	return encode(strData);
}

function IEVersion() {
	var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
	var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1; //判断是否IE<11浏览器
	var isEdge = userAgent.indexOf("Edge") > -1 && !isIE; //判断是否IE的Edge浏览器
	var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv:11.0") > -1;
	if (isIE) {
		var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
		reIE.test(userAgent);
		var fIEVersion = parseFloat(RegExp["$1"]);
		if (fIEVersion == 7) {
			return 7;
		} else if (fIEVersion == 8) {
			return 8;
		} else if (fIEVersion == 9) {
			return 9;
		} else if (fIEVersion == 10) {
			return 10;
		} else {
			return 6; //IE版本<=7
		}
	} else if (isEdge) {
		return 20; //edge
	} else if (isIE11) {
		return 11; //IE11
	} else {
		return 30; //不是ie浏览器
	}
}

var fromCharCode = String.fromCharCode;
// encoder stuff
var cb_utob = function (c) {
	if (c.length < 2) {
		var cc = c.charCodeAt(0);
		return cc < 0x80 ? c :
			cc < 0x800 ? (fromCharCode(0xc0 | (cc >>> 6)) +
				fromCharCode(0x80 | (cc & 0x3f))) :
				(fromCharCode(0xe0 | ((cc >>> 12) & 0x0f)) +
					fromCharCode(0x80 | ((cc >>> 6) & 0x3f)) +
					fromCharCode(0x80 | (cc & 0x3f)));
	} else {
		var cc = 0x10000 +
			(c.charCodeAt(0) - 0xD800) * 0x400 +
			(c.charCodeAt(1) - 0xDC00);
		return (fromCharCode(0xf0 | ((cc >>> 18) & 0x07)) +
			fromCharCode(0x80 | ((cc >>> 12) & 0x3f)) +
			fromCharCode(0x80 | ((cc >>> 6) & 0x3f)) +
			fromCharCode(0x80 | (cc & 0x3f)));
	}
};
var re_utob = /[\uD800-\uDBFF][\uDC00-\uDFFFF]|[^\x00-\x7F]/g;
var utob = function (u) {
	return u.replace(re_utob, cb_utob);
};
var _encode = function (u) {
	var isUint8Array = Object.prototype.toString.call(u) === '[object Uint8Array]';
	if (isUint8Array)
		return u.toString('base64')
	else
		return btoa(utob(String(u)));
}

if (typeof btoa !== 'function') btoa = func_btoa;

function func_btoa(input) {
	var str = String(input);
	var chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=';
	for (
		// initialize result and counter
		var block, charCode, idx = 0, map = chars, output = '';
		// if the next str index does not exist:
		//   change the mapping table to "="
		//   check if d has no fractional digits
		str.charAt(idx | 0) || (map = '=', idx % 1);
		// "8 - idx % 1 * 8" generates the sequence 2, 4, 6, 8
		output += map.charAt(63 & block >> 8 - idx % 1 * 8)
	) {
		charCode = str.charCodeAt(idx += 3 / 4);
		if (charCode > 0xFF) {
			throw new InvalidCharacterError("'btoa' failed: The string to be encoded contains characters outside of the Latin1 range.");
		}
		block = block << 8 | charCode;
	}
	return output;
}

function encode(u, urisafe) {
	return !urisafe ?
		_encode(u) :
		_encode(String(u)).replace(/[+\/]/g, function (m0) {
			return m0 == '+' ? '-' : '_';
		}).replace(/=/g, '');
}

var bUseHttps = false;

function initAddsOn(serverUrl, callback){
	var addsOnList = [
		{
			addonType: "wps",
			cmd: "enable",
			name: "WpsOAAssist",
			online: "true",
			url: serverUrl + "/wps/plugin/wps/"
		}
	];
	var count = addsOnList.length;
	for(var i=0,len=addsOnList.length;i<len;i++){
		var item = addsOnList[i];
		var data = FormatSendData(item);
		var req = { url: "http://localhost:58890/deployaddons/runParams", type: "POST" };
		startWps(req, data, function (res) {
			if (res.status == 0) {
				if (count ==1 && callback) {
					callback.call();
				} else {
					count--;
				}
			} else {
				alert(res.message);
			}
		});
	}
}

function _WppInvoke(funcs) {
    var info = {};
    info.funcs = funcs;
    var func = bUseHttps ? WpsInvoke.InvokeAsHttps : WpsInvoke.InvokeAsHttp
    func(WpsInvoke.ClientType.wpp, // 组件类型
        "WppOAAssist", // 插件名，与wps客户端加载的加载的插件名对应
        "dispatcher", // 插件方法入口，与wps客户端加载的加载的插件代码对应，详细见插件代码
        info, // 传递给插件的数据
        function (result) { // 调用回调，status为0为成功，其他是错误
            if (result.status) {
                if (bUseHttps && result.status == 100) {
                    WpsInvoke.AuthHttpesCert('请在稍后打开的网页中，点击"高级" => "继续前往"，完成授权。')
                    return;
                }
                alert(result.message)

            } else {
				window.close();
				window.parent && window.parent.closeLayer && window.parent.closeLayer();
            }
        })
}


function _WpsInvoke(funcs) {
    var info = {};
    info.funcs = funcs;
    var func = bUseHttps ? WpsInvoke.InvokeAsHttps : WpsInvoke.InvokeAsHttp
    func(WpsInvoke.ClientType.wps, // 组件类型
        "WpsOAAssist", // 插件名，与wps客户端加载的加载的插件名对应
        "dispatcher", // 插件方法入口，与wps客户端加载的加载的插件代码对应，详细见插件代码
        info, // 传递给插件的数据
        function (result) { // 调用回调，status为0为成功，其他是错误
            if (result.status) {
                if (bUseHttps && result.status == 100) {
                    WpsInvoke.AuthHttpesCert('请在稍后打开的网页中，点击"高级" => "继续前往"，完成授权。')
                    return;
                }
                alert(result.message)

            } else {
            	window.close();
				window.parent && window.parent.closeLayer && window.parent.closeLayer();
            }
        })
}

function _EtInvoke(funcs) {
    var info = {};
    info.funcs = funcs;
    var func = bUseHttps ? WpsInvoke.InvokeAsHttps : WpsInvoke.InvokeAsHttp
    func(WpsInvoke.ClientType.et, // 组件类型
        "EtOAAssist", // 插件名，与wps客户端加载的加载的插件名对应
        "dispatcher", // 插件方法入口，与wps客户端加载的加载的插件代码对应，详细见插件代码
        info, // 传递给插件的数据
        function (result) { // 调用回调，status为0为成功，其他是错误
            if (result.status) {
                if (bUseHttps && result.status == 100) {
                    WpsInvoke.AuthHttpesCert('请在稍后打开的网页中，点击"高级" => "继续前往"，完成授权。')
                    return;
                }
                alert(result.message)

            } else {
				window.close();
				window.parent && window.parent.closeLayer && window.parent.closeLayer();
            }
        })
}
