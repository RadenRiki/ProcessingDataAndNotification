Step pertama adalah menjalankan aplikasi, akan ada pop up allow notification, setelah di allow akan terlihat main screen dan tombol tampilkan matkul, setelah tombol dipencet akan langsung menampilkan matkul dan juga akan ada notifikasi di notification center

Alur:
User buka app → MainActivity tampil.
User menekan tombol di MainScreen.
MainScreen memanggil viewModel.loadCourses().
MainViewModel:
Set isLoading = true
Di background, memanggil CourseRepository.loadCoursesFromAssets()
Mendapat List<Course> dari JSON
Update state: isLoading = false, courses = data
Memanggil NotificationHelper.showNotification()
UI otomatis ter-update (list muncul), dan notifikasi lokal tampil di notification bar.
