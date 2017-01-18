'use strict'

let del = require('del'),
    gulp = require('gulp'),
    sass = require('gulp-sass'),
    uglify = require('gulp-uglify'),
    htmlmin = require('gulp-htmlmin'),
    vinylPaths = require('vinyl-paths'),
    cleanCss = require('gulp-clean-css'),
    sourcemaps = require('gulp-sourcemaps'),
    autoprefixer = require('gulp-autoprefixer'),
    browserSync = require('browser-sync').create(),
    reload = browserSync.reload

// 文件目录
const PATH = {
    assets: {
        root: '../../assets/pao-wen-quan/',
        script: '../../assets/pao-wen-quan/script/',
        style: '../../assets/pao-wen-quan/style/',
        html: '../../assets/pao-wen-quan/',
        bgimg: '../../assets/pao-wen-quan/bgimg/',
        image: '../../assets/pao-wen-quan/image/',
        lib: '../../assets/pao-wen-quan/script/lib/'
    },
    prd: {
        root: 'prd/',
        script: 'prd/script/',
        style: 'prd/style/',
        html: 'prd/html',
        bgimg: 'prd/bgimg/',
        image: 'prd/image/',
        lib: 'prd/script/lib/'
    },
    src: {
        sass: 'src/style/usage/pao-wen-quan/style.scss',
        html: 'src/html/*.html',
        script: 'src/script/index.js',
        bgimg: 'src/bgimg/**/*',
        image: 'src/image/**/*',
        lib: 'src/script/lib/*.js'
    },
    github: {
        dir: 'C:/Users/Roland Reed/Documents/GitHub/campus2017'
    }
}

// 清除文件
gulp.task('bgimg-clean', () => {
    return gulp.src(PATH.prd.bgimg)
        .pipe(vinylPaths(del))
})

gulp.task('image-clean', () => {
    return gulp.src(PATH.prd.image)
        .pipe(vinylPaths(del))
})

gulp.task('lib-clean', () => {
    return gulp.src(PATH.prd.lib)
        .pipe(vinylPaths(del))
})

// 复制文件
gulp.task('bgimg-copy', ['bgimg-clean'], () => {
    return gulp.src(PATH.src.bgimg)
        .pipe(gulp.dest(PATH.prd.bgimg))
        .pipe(gulp.dest(PATH.assets.bgimg))
})

gulp.task('image-copy', ['image-clean'], () => {
    return gulp.src(PATH.src.image)
        .pipe(gulp.dest(PATH.prd.image))
        .pipe(gulp.dest(PATH.assets.image))
})

gulp.task('lib-copy', ['lib-clean'], () => {
    return gulp.src(PATH.src.lib)
        .pipe(gulp.dest(PATH.prd.lib))
        .pipe(gulp.dest(PATH.assets.lib))
})

// 静态文件任务
gulp.task('bgimg', ['bgimg-copy'])

gulp.task('image', ['image-copy'])

gulp.task('lib', ['lib-copy'])

// Browser Sync
gulp.task('serve', () => {
    browserSync.init({
        startPath: '/html',
        baseDir: PATH.prd.root,
        server: PATH.prd.root
    })

    gulp.watch(PATH.src.sass, ['sass'])
    gulp.watch(PATH.src.html, ['html-watch'])
    gulp.watch(PATH.src.script, ['js-watch'])
    gulp.watch(PATH.src.bgimg, ['bgimg'])
    gulp.watch(PATH.src.image, ['image'])
    gulp.watch(PATH.src.lib, ['lib'])
})

// HTML文件任务
gulp.task('html', () => {
    return gulp.src(PATH.src.html)
        .pipe(htmlmin({
            collapseWhitespace: true,
            removeComments: true
        }).on('error', e => {
            console.error(e)
            this.emit('end')
        }))
        .pipe(gulp.dest(PATH.prd.html))
        .pipe(gulp.dest(PATH.assets.html))
})

// 完成HTML任务后重载页面
gulp.task('html-watch', ['html'], reload)

// 编译Sass并热重载CSS
gulp.task('sass', () => {
    return gulp.src(PATH.src.sass)
        .pipe(sass().on('error', e => {
            console.error(e)
            this.emit('end')
        }))
        .pipe(autoprefixer({
            browsers: ['last 2 versions']
        }))
        .pipe(cleanCss())
        .pipe(gulp.dest(PATH.prd.style))
        .pipe(gulp.dest(PATH.assets.style))
        .pipe(browserSync.reload({
            stream: true
        }))
})

// JS文件任务
gulp.task('js', () => {
    return gulp.src(PATH.src.script)
        .pipe(sourcemaps.init())
        .pipe(uglify())
        .pipe(sourcemaps.write('./maps', {
            sourceMappingURL: 'sourceMappingURL=data:application/jsoncharset=utf-8base64'
        }))
        .pipe(gulp.dest(PATH.prd.script))
        .pipe(gulp.dest(PATH.assets.script))
})

// 完成JS任务后重载页面
gulp.task('js-watch', ['js'], reload)

// gulp build任务
gulp.task('build', ['bgimg', 'image', 'lib', 'html', 'sass', 'js'], () => {
    gulp.src(['.eslintrc.json', './src/**/*', 'package.json', 'README.md', 'gulpfile.js'], { base: '.' })
        .pipe(gulp.dest(PATH.github.dir))
})

// gulp默认任务：启动Browser Sync
gulp.task('default', ['serve'])