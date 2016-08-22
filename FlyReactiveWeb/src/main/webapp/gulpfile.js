var gulp = require('gulp'),
	concat = require('gulp-concat'),
	uglify = require('gulp-uglify'),
	watch = require('gulp-watch');

gulp.task('loco', function() {
	console.log("Loquitoooo :V");
});

gulp.task('demo', function () {
	return watch('js/sources/*.js', function() {
		gulp.src('js/sources/*.js')
			.pipe(concat('todo.js'))
			.pipe(uglify())
			.pipe(gulp.dest('js/build/'));		
	});
});

gulp.task('default', ['loco','demo']);