
var isReady=false;var onReadyCallbacks=[];
var isServiceReady=false;var onServiceReadyCallbacks=[];
var __uniConfig = {"pages":["pages/index/index","pages/testpage","pages/testpage","pages/user/login","pages/index/detail","pages/index/edit","pages/check-in/index","pages/check-in/insert","pages/dispatch/index","pages/archives/index","pages/scan/byId","pages/scan/index","pages/scan/detail","pages/archives/house-info","pages/archives/floor-info","pages/archives/room-info","pages/check/temp","pages/check/todo","pages/check/location-select","pages/check/do-check","pages/check/plan","pages/check/room","pages/check/equipment","pages/check/temporary","pages/patrol/index","pages/patrol/equipment","pages/patrol/history","pages/tscan/index"],"window":{"navigationBarTextStyle":"black","navigationBarTitleText":"uni-app","navigationBarBackgroundColor":"#ffffff","backgroundColor":"#F8F8F8"},"nvueCompiler":"uni-app","nvueStyleCompiler":"weex","renderer":"auto","splashscreen":{"alwaysShowBeforeRender":true,"autoclose":false},"appname":"实物ID","compilerVersion":"3.2.9","entryPagePath":"pages/index/index","networkTimeout":{"request":60000,"connectSocket":60000,"uploadFile":60000,"downloadFile":60000}};
var __uniRoutes = [{"path":"/pages/index/index","meta":{"isQuit":true},"window":{"navigationBarTitleText":"首页","navigationStyle":"custom"}},{"path":"/pages/testpage","meta":{},"window":{"navigationBarTitleText":"111"}},{"path":"/pages/user/login","meta":{},"window":{"navigationBarTitleText":"","navigationStyle":"custom","bounce":"none"}},{"path":"/pages/index/detail","meta":{},"window":{"navigationBarTitleText":"资产详情","bounce":"none","titleNView":{"buttons":[{"backgroundColor":"#249586","color":"#249586","fontSize":"24rpx","text":"编辑 "}]}}},{"path":"/pages/index/edit","meta":{},"window":{"navigationBarTitleText":"资产编辑","bounce":"none"}},{"path":"/pages/check-in/index","meta":{},"window":{"navigationBarTitleText":"一键入库"}},{"path":"/pages/check-in/insert","meta":{},"window":{"navigationBarTitleText":"新增入库"}},{"path":"/pages/dispatch/index","meta":{},"window":{"navigationBarTitleText":"一键发放"}},{"path":"/pages/archives/index","meta":{},"window":{"navigationBarTitleText":"一室一档"}},{"path":"/pages/scan/byId","meta":{},"window":{"navigationBarTitleText":"物资详情"}},{"path":"/pages/scan/index","meta":{},"window":{"navigationBarTitleText":"物资编辑"}},{"path":"/pages/scan/detail","meta":{},"window":{"navigationBarTitleText":"物资详情结果"}},{"path":"/pages/archives/house-info","meta":{},"window":{"navigationBarTitleText":""}},{"path":"/pages/archives/floor-info","meta":{},"window":{"navigationBarTitleText":""}},{"path":"/pages/archives/room-info","meta":{},"window":{"navigationBarTitleText":""}},{"path":"/pages/check/temp","meta":{},"window":{"navigationBarTitleText":"临时盘点"}},{"path":"/pages/check/todo","meta":{},"window":{"navigationBarTitleText":"盘点计划"}},{"path":"/pages/check/location-select","meta":{},"window":{"navigationBarTitleText":"选择盘点地点"}},{"path":"/pages/check/do-check","meta":{},"window":{"navigationBarTitleText":" "}},{"path":"/pages/check/plan","meta":{},"window":{"navigationBarTitleText":"盘点计划","enablePullDownRefresh":true}},{"path":"/pages/check/room","meta":{},"window":{"navigationBarTitleText":"选择盘点地点","enablePullDownRefresh":true}},{"path":"/pages/check/equipment","meta":{},"window":{"navigationBarTitleText":"盘点设备","enablePullDownRefresh":true}},{"path":"/pages/check/temporary","meta":{},"window":{"navigationBarTitleText":"临时盘点","enablePullDownRefresh":true}},{"path":"/pages/patrol/index","meta":{},"window":{"navigationBarTitleText":"移动检修","enablePullDownRefresh":true}},{"path":"/pages/patrol/equipment","meta":{},"window":{"navigationBarTitleText":"巡检设备"}},{"path":"/pages/patrol/history","meta":{},"window":{"navigationBarTitleText":"巡检记录"}},{"path":"/pages/tscan/index","meta":{},"window":{"navigationBarTitleText":"扫描测试(上海)"}}];
__uniConfig.onReady=function(callback){if(__uniConfig.ready){callback()}else{onReadyCallbacks.push(callback)}};Object.defineProperty(__uniConfig,"ready",{get:function(){return isReady},set:function(val){isReady=val;if(!isReady){return}const callbacks=onReadyCallbacks.slice(0);onReadyCallbacks.length=0;callbacks.forEach(function(callback){callback()})}});
__uniConfig.onServiceReady=function(callback){if(__uniConfig.serviceReady){callback()}else{onServiceReadyCallbacks.push(callback)}};Object.defineProperty(__uniConfig,"serviceReady",{get:function(){return isServiceReady},set:function(val){isServiceReady=val;if(!isServiceReady){return}const callbacks=onServiceReadyCallbacks.slice(0);onServiceReadyCallbacks.length=0;callbacks.forEach(function(callback){callback()})}});
service.register("uni-app-config",{create(a,b,c){if(!__uniConfig.viewport){var d=b.weex.config.env.scale,e=b.weex.config.env.deviceWidth,f=Math.ceil(e/d);Object.assign(__uniConfig,{viewport:f,defaultFontSize:Math.round(f/20)})}return{instance:{__uniConfig:__uniConfig,__uniRoutes:__uniRoutes,global:void 0,window:void 0,document:void 0,frames:void 0,self:void 0,location:void 0,navigator:void 0,localStorage:void 0,history:void 0,Caches:void 0,screen:void 0,alert:void 0,confirm:void 0,prompt:void 0,fetch:void 0,XMLHttpRequest:void 0,WebSocket:void 0,webkit:void 0,print:void 0}}}});
