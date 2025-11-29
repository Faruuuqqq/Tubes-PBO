# Tubes-PBO

## Plan Awal Mau Dibuat MVC Begini
#### main (Untuk file utama/Main class nanti)

#### config (Tempat menyimpan file koneksi database)
- ini untuk koneksi ke database nya

#### model (Tempat menyimpan objek data seperti Pegawai, Pelanggan)
- isinya cuman deklarasi dari skema database kita, sama setter & getter

#### view (Tempat desain form GUI)
- jadi isinya cuman UI aja, tapi paling nanti ada logic nya dikit, sisanya untuk button itu tinggal panggil method dari controller aja.

#### controller (Tempat logika program)
- Menerima data dari View
- Mengubahnya menjadi Model
- Mengirim perintah SQL seperti `INSERT`, `SELECT`, `DELETE` ke database


##  Kenapa pake MVC?
- biar lebih enak kerjain bareng nya
- harusnya lebih mudah cari error dan buat validasi
- pastinya lebih rapi, semoga pak akmal suka