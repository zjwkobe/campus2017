exports.config = function () {
    return {
        // [配置] 项目资源入口
        export: ['./page/hotel/index.js'],
        // [配置] webpack
        modifyWebpackConfig: function (config) {
            // [配置] chunk 的路径
            config.output.local.publicPath = '//127.0.0.1/homework-4/prd/';

            // [配置] 项目中的别名，推荐所有的别名都以 $ 开头，既能一眼识别出是别名，也能避免命名冲突
            config.resolve = {
                alias: {
                    '$yo': 'yo3',
                    '$yo-config': '/src/yo-config',
                    '$yo-component': 'yo3/component',
                    '$router': 'yo-router',
                    '$common': '/src/common',
                    '$component': '/src/component'
                }
            };

            return config;
        }
    }
};