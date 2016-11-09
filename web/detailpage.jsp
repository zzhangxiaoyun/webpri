<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dytt.service.DetailService" %>
<%@ page import="com.dytt.entity.Detail" %>
<%@ page import="com.oreilly.servlet.Base64Decoder" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//String baseUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
    String baseUrl = "http://www.bytespace.cn";
    String apkurl = "http://www.bytespace.cn/dianying.apk";
    String p = request.getParameter("P");
    int id = 20;
    try{
        id = Integer.parseInt(Base64Decoder.decode(p));
    }catch (Exception e){
        e.printStackTrace();
    }
    Detail detail = DetailService.getDetail(id);
    if(detail == null){// 容错
        detail = new Detail();
        detail.setDownloadurls(new ArrayList<String>());
        detail.setImgurls(new ArrayList<String>());
    }
    int index = detail.getContent().indexOf("◎简　　介");
    if(index >-1){
        String content = detail.getContent();
        content = content.substring(index+6,content.length());
        if(content.length()>0){
            detail.setContent(content);
        }
    }
%>

<!DOCTYPE html>
<html mip>
<head>
    <meta charset="utf-8"/>
    <meta name="referrer" content="always">
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <title><%=detail.getName()%></title>
    <link rel="stylesheet" type="text/css" href="https://mipcache.bdstatic.com/static/mipmain-v1.1.0.css">
    <style mip-custom>html {
        font-size: 100px
    }

    body {
        font-family: Arial, Helvetica, sans-serif;
        text-align: left;
        -webkit-text-size-adjust: none;
        background: #f5f5f5
    }

    ol, ul, dl {
        list-style: none
    }

    a {
        text-decoration: none
    }

    .item-wrap {
        background-color: #fff;
        color: #333;
        max-width: 600px;
    }

    .floatl {
        float: left
    }

    .floatr {
        float: right
    }

    .clear:after {
        content: '';
        display: block;
        clear: both
    }

    p {
        -webkit-margin-before: 0rem;
        -webkit-margin-after: 0rem;
        -webkit-margin-start: 0;
        -webkit-margin-end: 0
    }

    h1, h2, h3 {
        font-weight: normal;
        -webkit-margin-before: 0rem;
        -webkit-margin-after: 0rem;
        -webkit-margin-start: 0;
        -webkit-margin-end: 0
    }

    .empty-notice {
        position: absolute;
        top: 36%;
        left: 50%;
        transform: translateX(-50%) translateY(-50%);
        -webkit-transform: translateX(-50%) translateY(-50%);
        text-align: center
    }

    .empty-notice .empty-img {
        width: 90px;
        height: 90px;
        display: inline-block;
        background: url(data:image/png;base64,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) no-repeat;
        background-size: 100% auto
    }

    .empty-notice .empty-text {
        font-size: .2rem;
        line-height: 1;
        margin-top: .07rem;
        color: #333
    }

    .hide {
        display: none
    }

    .show {
        display: block
    }

    .article-header {
        background-color: #f5f5f5;
        margin-top: 22px;
        padding: 0 16px
    }

    .article-header h1 {
        font-size: .22rem;
        line-height: .32rem
    }

    .article-info {
        background-color: #f5f5f5;
        margin-top: 9px;
        line-height: 1;
        padding: 0 16px
    }

    .article-info span {
        font-size: 13px;
        color: #999;
        margin-right: 8px
    }

    .article-info span:first-child {
        padding-left: 0
    }

    .article-infoBaiJiaHao {
        background-color: #f5f5f5;
        margin-top: 17px;
        padding: 0 16px;
        line-height: 1
    }

    .article-infoBaiJiaHao a {
        -webkit-tap-highlight-color: rgba(0, 0, 0, 0)
    }

    .article-infoBaiJiaHao a.info-icon {
        height: 35px;
        width: 35px;
        border-radius: 50%;
        overflow: hidden
    }

    .article-infoBaiJiaHao a.info-icon img {
        border-radius: 50%
    }

    .article-infoBaiJiaHao span.info-name {
        display: table;
        padding: 0 0 6px 8px;
        font-size: 16px;
        line-height: 1;
        color: #405b95
    }

    .article-infoBaiJiaHao span.info-src {
        position: relative;
        color: #405b95
    }

    .article-infoBaiJiaHao span.info-src::after {
        content: '';
        position: absolute;
        top: 0;
        right: 0;
        width: 1px;
        height: 100%;
        background-color: #dfdfdf
    }

    .article-infoBaiJiaHao span.info-time {
        padding-left: 0
    }

    .article-infoBaiJiaHao span {
        font-size: 16px;
        line-height: 1;
        color: #999;
        padding: 0 8px
    }

    .article-body {
        background-color: #f5f5f5;
        text-align: justify;
        padding: 0 16px
    }

    .article-body p {
        line-height: .26rem;
        font-size: .16rem;
        color: #333;
        max-width: 100%;
        word-wrap: break-word;
        word-break: break-all
    }

    .article-body p:first-child {
        margin-top: .3rem
    }

    .article-body p + p {
        margin-top: .2rem
    }

    .article-body p + div.mip-img-container {
        margin-top: .12rem
    }

    .article-body p:last-child {
        margin-bottom: -0.05rem
    }

    .article-body .mip-img-container {
        text-align: center;
        position: relative;
        background: #f7f7f7 url(//m.baidu.com/static/search/image_default.png1) no-repeat center center;
        max-width: 100%;
        margin: .17rem auto
    }

    .article-body .mip-img-container + p {
        margin-top: .17rem
    }

    .article-body .mip-img-container:last-child {
        margin-bottom: 0;
        padding-bottom: .05rem
    }

    .article-footer {
        padding: 25px 16px 23px 16px;
        background-color: #f5f5f5
    }

    .article-footer .article-label {
        background-color: #f5f5f5;
        margin-bottom: .3rem
    }

    .article-footer .article-label a {
        background-color: #fff;
        display: inline-block;
        height: .24rem;
        margin-right: .08rem;
        font-size: .13rem;
        line-height: .24rem;
        color: #666;
        box-sizing: border-box;
        padding: 0 10px
    }

    .article-footer span, .article-footer span a {
        font-size: 16px;
        color: #999
    }

    .related-news {
        border-bottom: 1px solid #eaeaea;
        border-top: 1px solid #eaeaea
    }

    .related-news h2 {
        margin: 0 16px;
        font-size: 16px;
        line-height: 1;
        padding: 14px 0 13px 0;
        color: #999;
        border-bottom: 1px solid #e6e6e6;
        position: relative
    }

    .related-news h2::after {
        position: absolute;
        content: '';
        height: 1px;
        width: 64px;
        background-color: #38f;
        left: 0;
        top: 100%
    }

    .related-news .news-item a {
        margin: 0 16px;
        border-bottom: 1px solid #f0f0f0;
        padding: 14px 0;
        display: block
    }

    .related-news .news-item a.a-visited h2 {
        color: #aaa
    }

    .related-news .news-item h3 {
        font-weight: normal;
        color: #333;
        overflow: hidden;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical
    }

    .related-news .news-item .text-content {
        margin-left: 14px
    }

    .related-news .news-item .news-from {
        margin-top: 6px;
        margin-left: 14px
    }

    .related-news .news-item .news-from span {
        margin-right: 5px;
        font-size: 13px;
        line-height: 1;
        color: #999;
        display: inline-block;
        line-height: 1;
        vertical-align: top
    }

    .news-item:last-child a {
        border-bottom: 0
    }

    .related-news .tpl-2 a {
        position: relative;
        display: -webkit-box;
        display: -moz-box;
        display: -ms-flexbox;
        display: -webkit-flex;
        display: flex;
        display: box;
        -webkit-box-align: center;
        -moz-box-align: center;
        -ms-flex-align: center;
        align-items: center;
        -webkit-align-items: center
    }

    .related-news .tpl-2 .pic-content {
        width: 33.65%;
        position: relative
    }

    .related-news .tpl-2 .pic-content mip-img {
        position: relative;
        top: 0;
        left: 0
    }

    .related-news .tpl-2 .info-content {
        width: 67.88%;
        -webkit-box-ordinal-group: 2;
        -moz-box-ordinal-group: 2;
        -ms-box-ordinal-group: 2;
        -webkit-order: 2;
        order: 2
    }

    .related-news .tpl-2 .text-content h3 {
        padding: 0;
        margin: 0;
        -webkit-line-clamp: 2;
        font-size: 18px;
        line-height: 26px
    }

    .related-news .tpl-1 .news-from {
        margin-top: 9px
    }

    .related-news .tpl-1 .text-content h3 {
        -webkit-line-clamp: 2
    }

    .recentArticle {
        padding: 0 16px;
        margin-top: 8px;
        border-top: 1px solid #eaeaea;
        border-bottom: 1px solid #eaeaea
    }

    .recentArticle h2 {
        font-size: 16px;
        line-height: 1;
        padding: 14px 0 13px 0;
        color: #999;
        border-bottom: .01px solid #e6e6e6;
        position: relative
    }

    .recentArticle h2::after {
        position: absolute;
        content: '';
        height: 1px;
        width: 96px;
        background-color: #38f;
        left: 0;
        top: 100%
    }

    .recentArticle li {
        border-bottom: 1px solid #f0f0f0;
        position: relative;
        display: -webkit-box;
        display: -moz-box;
        display: -ms-flexbox;
        display: -webkit-flex;
        display: flex;
        -webkit-box-align: center;
        -moz-box-align: center;
        -ms-flex-align: center;
        align-items: center;
        -webkit-align-items: center
    }

    .recentArticle li:last-child {
        border-bottom: 0
    }

    .recentArticle a {
        box-sizing: border-box;
        padding: 14px 0;
        display: block;
        color: #333;
        overflow: hidden
    }

    .recentArticle a span {
        font-size: 15px;
        display: -webkit-box;
        -webkit-line-clamp: 1;
        -webkit-box-orient: vertical;
        overflow: hidden;
        text-overflow: ellipsis
    }

    .recentArticle a.a-visited h2 {
        color: #aaa
    }

    .article-notice {
        padding: 12px 16px;
        border-bottom: 1px solid #eaeaea;
        font-size: 13px;
        line-height: 1;
        color: #999;
        position: relative;
        background-color: #f5f5f5
    }

    .article-notice h2 {
        font-size: 13px;
        line-height: 1
    }

    .article-notice p {
        font-size: 13px;
        line-height: 1;
        margin-top: 8px;
        word-break: break-all
    }

    .article-notice a {
        position: absolute;
        right: 16px;
        top: 12px;
        color: #333
    }

    .article-notice a.a-visited h2 {
        color: #aaa
    }</style>
</head>
<body>
<script>window.sSession = window.sSession || {};
window.sSession = {
    newIndexTplName: '',
    ref: 'index_iphone',
    lid: '2809089219863461141',
    indexnid: '11026711795180618963',
    pn: '0',
    rn: '0',
    sids: '102572_104494_100272_102478_107851_103569_109639_106323_110782_107799_110610_110684_110394_110717_111169_110298_110655_107311_110710_110349_110031_110400_110646_109656_111216_110085',
    osid: "",
    browserid: "",
    logid: "2809089219863461141",
    prepath: 'ssid=0#from=0#pu=',
    from: '0'
};</script>
<script type="text/javascript">(function () {
    if (self !== top) {
        window.parent.postMessage({event: 'getMpDomReady'}, '*');
    };
    font();
})();
function font() {
    var r = localStorage.getItem("min_detail_font_size") || 1;
    var h = document.getElementsByTagName("html");
    h[0].style.fontSize = r * 100 + 'px';
}</script>
<div id="detail-page">
    <div class="item-wrap">
        <mip-news-head>
            <header class="article-header" data-pos="1">
                <div class="article-title"><h1><%=detail.getName()%></h1></div>
            </header>
        </mip-news-head>
    </div>
    <div class="item-wrap">
        <div class="article-info">
            <span class="info-src">
                <a href="<%=apkurl%>">电影天堂App</a>
            </span>

            <span class="info-date"><%=detail.getTime()%></span></div>
    </div>
    <div class="item-wrap">
        <article>
            <div class="article-body">
                <%
                    if(detail.getImgurls().size()>0){
                %>
                    <%--<div class="mip-img-container">--%>
                        <%--<mip-img layout="responsive" height="740" width="640"--%>
                                 <%--src="<%=detail.getImgurls().remove(0) %>"></mip-img>--%>
                    <%--</div>--%>
                <div class="mip-img-container">
                    <img src="<%=detail.getImgurls().remove(0) %>" />
                </div>
                <%
                    }
                %>


                <p><%=detail.getContent()%></p>
                <%
                    for(String url : detail.getImgurls()){
                %>
                <div class="mip-img-container">
                    <img src="<%=url%>"/>
                </div>
                <%
                    }
                %>

                <%
                    for(String url: detail.getDownloadurls()){
                %>
                        <p>
                            <a href="<%=url%>" target="_blank"><%=url%></a>
                        </p>
                <%
                    }
                %>
            </div>

            <footer class="article-footer">
                <span class="info-src">
                <a href="<%=apkurl%>" target="_blank" >下载电影天堂app</a>
                </span>
            </footer>

        </article>
    </div>
</div>
<script src="https://mipcache.bdstatic.com/static/mipmain-v1.1.0.js?v=md5"></script>
<script type="text/javascript">define("mTunnel", function () {
    var e = {};
    var c = {};
    var i = {TWO_WAY: "two-way"};
    var b = "PM_REQUEST";
    var a = "PM_RESPONSE";
    window.addEventListener("message", function (l) {
        var k = l.data || {};
        if (k.type === i.TWO_WAY) {
            if (k.sentinel === b) {
                f(l.source, $.extend({}, k, {sentinel: a, data: g(k.event, k) || {}}), false)
            } else {
                if (k.sentinel === a) {
                    k.deferTimer && window.clearTimeout(k.deferTimer);
                    e[k.sessionId] && e[k.sessionId].resolve(k);
                    delete e[k.sessionId];
                    delete k.deferTimer
                }
            }
        } else {
            g(k.event, k)
        }
    }, false);
    function f(m, l, o) {
        var k = {};
        var n = j();
        if (o) {
            $.extend(k, l, {type: i.TWO_WAY, sentinel: b, sessionId: n});
            e[n] = $.Deferred();
            k.deferTimer = setTimeout(function () {
                e[n].reject("timeout")
            }, 1000)
        } else {
            k = l
        }
        m.postMessage(k, "*");
        return o && e[n].promise()
    }

    function j() {
        return ((+new Date) * 1000 + Math.ceil(Math.random() * 1000)).toString(36)
    }

    function d(k, l) {
        c[k] = l
    }

    function h(k) {
        delete c[k]
    }

    function g(l, k) {
        if (typeof c[l] === "function") {
            return c[l](k)
        }
    }

    return {addRequestHandle: d, deleteRequestHandle: h, sendMessage: f}
});
;</script>
<script src="https://gss0.bdstatic.com/5bd1bjqh_Q23odCf/static/recdetail/js/mip-plugin/mipBaijiahaoInfo.js?v=md5"></script>
<script type="text/javascript">define("lib/log", function (b, a, c) {
    var f = window.sSession;

    function e(g, i) {
        var h = {
            tcreq4log: 1 + "&" + f.prepath.replace(/#/g, "&"),
            ct: 0,
            cst: 0,
            ref: f.ref || "",
            lid: f.lid || "",
            qid: f.lid || "",
            index_qid: f.indexqid || "",
            w: f.pn + "_" + f.rn + "_" + encodeURIComponent(f.query || "")
        };
        if (f.sids) {
            h.sid = f.sids
        }
        $.extend(h, g);
        d("//m.baidu.com/tc", h, i)
    }

    function d(h, g, o) {
        var n = Date.now(), k = window[n] = new Image(), l = "", m = null;
        k.onload = k.onerror = k.onabort = function () {
            window[n] = null;
            if (m) {
                clearTimeout(m);
                m = null;
                o && o()
            }
        };
        g = g || {};
        g.r = n;
        for (var j in g) {
            if (g.hasOwnProperty(j)) {
                l += "&" + j + "=" + g[j]
            }
        }
        k.src = h + "?" + l.slice(1);
        if (typeof o === "function") {
            m = setTimeout(function () {
                m = null;
                o()
            }, 500)
        }
    }

    return {send: e}
});
;
(function () {
    var b = {data: {}};
    var a;
    if ((a = window.location.search.match(/sid=(\d+)/)) && a[1]) {
        b.data.sid = a[1]
    }
    $.extend($, {
        ajaxget: function (c) {
            var f = {type: "GET"};
            var e = $.extend(true, {}, b);
            var d = $.extend(true, e, f, c);
            return $.ajax(d)
        }, ajaxpost: function (c) {
            var f = {type: "POST"};
            var e = $.extend(true, {}, b);
            var d = $.extend(true, e, f, c);
            return $.ajax(d)
        }, ajaxjsonp: function (c) {
            var f = {dataType: "jsonp"};
            var e = $.extend(true, {}, b);
            var d = $.extend(true, e, f, c);
            return $.ajax(d)
        }
    })
})();
;
define("lib/utils", function () {
    (function () {
        var e = window;

        function c() {
            var g = 1;
            if ("orientation" in window) {
                switch (window.orientation) {
                    case 90:
                    case -90:
                        g = -1;
                        break;
                    case 0:
                    case 180:
                        g = 1;
                        break;
                    default:
                }
            } else {
                g = window.innerHeight > window.innerWidth ? 1 : -1
            }
            return g
        }

        var f = [];
        var b = "onorientationchange" in window, d = b ? "orientationchange" : "resize";
        var a = c();
        $(window).on(d, function () {
            var g = c();
            if (g !== a) {
                a = g;
                setTimeout(function () {
                    window.scrollTo(0, 1)
                }, 200)
            }
        })
    })();
    (function () {
        var b = window.B || (window.B = {});
        var a = function (e, d) {
            var f = b[e] = {};
            f.enabled = b.lsSupport;
            if (!f.enabled) {
                f.getObjItem = f.getObj = f.getItem = f.get = f.key = function () {
                    return null
                };
                f.setObj = f.setObjItem = f.updateObj = f.updateObjItem = f.setItem = f.set = f.removeItem = f.remove = f.clear = function () {
                };
                f.length = function () {
                    return 0
                };
                f.keys = function () {
                    return []
                };
                return
            }
            var c = window[d];
            f.getItem = function (g) {
                return c.getItem(g)
            };
            f.setItem = function (g, h) {
                c.setItem(g, h)
            };
            f.removeItem = function (g) {
                c.removeItem(g)
            };
            f.getObjItem = function (g, h) {
                var k = this.getItem(g), j = null;
                try {
                    j = JSON.parse(k);
                    h && (j = j[h])
                } catch (i) {
                    j = null
                }
                return j
            };
            f.getObj = function (k, h, j, i) {
                var g = [k, h, j].join("_");
                return this.getObjItem(g, i)
            };
            f.get = function (j, h, i) {
                var g = [j, h, i].join("_");
                return f.getItem(g)
            };
            f.set = function (k, h, j, i) {
                var g = [k, h, j].join("_");
                f.setItem(g, i)
            };
            f.remove = function (j, h, i) {
                var g = [j, h, i].join("_");
                f.removeItem(g)
            };
            f.setObjItem = function (g, h) {
                this.setItem(g, JSON.stringify(h))
            };
            f.setObj = function (i, g, h, j) {
                this.setObjItem([i, g, h].join("_"), j)
            };
            f.updateObjItem = function (h, i, g) {
                var j = this.getObjItem(h);
                if (j != null) {
                    j[i] = g;
                    this.setObjItem(h, j)
                }
            };
            f.updateObj = function (k, h, j, i, g) {
                this.updateObjItem([k, h, j].join("_"), i, g)
            };
            f.keys = function (n, k) {
                var m = false, j;
                if (arguments.length == 2) {
                    j = [n, k].join("_");
                    m = new RegExp("^" + j)
                }
                var l = [];
                for (var h = 0, g = c.length; h < g; ++h) {
                    j = c.key(h);
                    if (!m || j.match(m)) {
                        l.push(j)
                    }
                }
                return l
            };
            f.clear = function () {
                c.clear()
            };
            f.length = function () {
                return c.length
            };
            f.key = function (g) {
                return c.key(g)
            }
        };
        a("ls", "localStorage");
        a("ss", "sessionStorage")
    })();
    (function () {
        var a = window.B || (window.B = {});
        a.tipsInfo = {
            tipsShow: function (f, d, c, b) {
                var e = a.ls.getObjItem("plus_tips_info");
                if (!e) {
                    e = {}
                }
                if (!e[f]) {
                    e[f] = {times: 0, name: d}
                }
                this.detect2Close(f, d, ++e[f].times, c, b).done(function () {
                    delete e[f]
                }).always(function () {
                    a.ls.setObjItem("plus_tips_info", e)
                })
            }, detect2Close: function (g, d, e, c, b) {
                var f = $.Deferred();
                if (e >= c) {
                    this.sendClose(g, d, b).done(function (h) {
                        if (h && +h.errno === 0) {
                            f.resolve()
                        }
                    }).fail(function () {
                        f.reject()
                    })
                } else {
                    f.reject()
                }
                return f
            }, sendClose: function (d, c, b) {
                return $.ajaxget({
                    url: "?action=setadstatus",
                    dataType: "json",
                    data: {place: c, child_place: b || "", aid: d}
                })
            }
        }
    })();
    B.cookie = function (d, e, c) {
        var b, g, a, f;
        if (arguments.length == 0) {
            return document.cookie
        }
        if (arguments.length > 1 && String(e) !== "[object Object]") {
            c = c || {};
            if (e === null || e === undefined) {
                c.expires = -1
            }
            if (typeof c.expires === "number") {
                b = c.expires;
                g = c.expires = new Date();
                g.setTime(g.getTime() + b)
            }
            e = String(e);
            return (document.cookie = [encodeURIComponent(d), "=", c.raw ? e : encodeURIComponent(e), c.expires ? "; expires=" + c.expires.toUTCString() : "", c.path ? "; path=" + c.path : "", c.domain ? "; domain=" + c.domain : "", c.secure ? "; secure" : ""].join(""))
        }
        c = e || {};
        f = c.raw ? function (h) {
            return h
        } : decodeURIComponent;
        return (a = new RegExp("(?:^|; )" + encodeURIComponent(d) + "=([^;]*)").exec(document.cookie)) ? f(a[1]) : null
    };
    B.asynJs = [];
    B.asynLoad = function (a) {
        if (!~B.asynJs.indexOf(a)) {
            B.asynJs.push(a)
        }
    }
});
require(["lib/utils"]);
;
define("recdetail/lib/thunder/mediaDuration", function () {
    var b = window;

    function a(f) {
        var d = this;
        var e = b.thunderConf || {};
        if (!e.startTime && b.performance) {
            e.startTime = b.performance.timing.domLoading
        }
        var g = $.extend({}, f);

        function c(i, h, j) {
            var k = setTimeout(function () {
                g.sessionTime = j;
                d.send(g);
                var l = (i + h) < 60000 ? (i + h) : 60000;
                c(h, l, j + i)
            }, i)
        }

        c(3000, 3000, 3000)
    }

    return {mName: "mediaDuration", mFunc: a}
});
;
var thunderPlugins = ["recdetail/lib/thunder/mediaDuration"];
define("recdetail/lib/thunder/thunder", function () {
    var c = window;

    function d(f) {
        var e = this;
        e.opt = f || {};
        e.baseParams = e.opt.baseParams || {};
        e.config(f, true);
        if (f.autoBind) {
            e.bind()
        }
    }

    d.prototype = {
        config: function (h, e) {
            var g = this;
            for (var f in h) {
                g.opt[f] = h[f]
            }
            g.baseURL = g.opt.baseURL || "//hpd.baidu.com/v.gif";
            g.domHook = g.opt.domHook;
            g.skipPrevent = !!g.opt.skipPrevent;
            g.logRegx = g.opt.logRegx || "(.*?):(.*?);";
            g.baseParams = b(g.baseParams, h.baseParams)
        }, send: function (h, n, e) {
            var o = this;
            o.__sendPreHook && o.__sendPreHook(h);
            var f = "l" + Date.now();
            var l = window[f] = new Image();
            var j = "";
            var g = null;
            e = e || o.baseURL;
            l.onload = l.onerror = l.onabort = function () {
                window[f] = null;
                if (g) {
                    clearTimeout(g);
                    g = null;
                    n && n();
                    o.__sendAfrHook && o.__sendAfrHook(h, false)
                }
            };
            h = h || {};
            h.r = f;
            var m = o.baseParams;
            h = b(b({}, m), h);
            for (var k in h) {
                if (h.hasOwnProperty(k)) {
                    j += "&" + k + "=" + encodeURIComponent(h[k])
                }
            }
            l.src = e + "?" + j.slice(1);
            if (typeof n === "function") {
                g = setTimeout(function () {
                    g = null;
                    n()
                }, 500)
            }
            o.__sendAfrHook && o.__sendAfrHook(h, true)
        }, bindLogHook: function (e) {
            var f = this;
            e.on("click", f.domHook, f.hookClick.bind(this, f))
        }, hookClick: function (j, l) {
            var n = this;
            var i = j.getAttribute(n.domHook);
            var k = j.getAttribute("href");
            var m = null;
            var f = {};
            var g = new RegExp(n.logRegx);
            var h = null;
            while ((h = g.exec(i))) {
                f[h[1]] = h[2]
            }
            if (j.tagName.toLowerCase() === "a" && k) {
                l.preventDefault();
                m = function () {
                    c.location.href = k
                }
            }
            n.send(n.baseURL, f, m)
        }
    };
    function b(g, f) {
        if (!f || !g) {
            return g
        }
        for (var e in f) {
            g[e] = f[e]
        }
        return g
    }

    thunderPlugins.forEach(function (f, e) {
        require([f], function (g) {
            d.prototype[g.mName] = g.mFunc
        })
    });
    var a = null;
    return {
        create: function (f, e) {
            return new d(f)
        }, get: function (e) {
            return a || (a = this.create(e))
        }
    }
});
;</script>
<script src="https://gss0.bdstatic.com/5bd1bjqh_Q23odCf/static/recdetail/js/index_6ac2b9ec.js"></script>
</body>
</html>
