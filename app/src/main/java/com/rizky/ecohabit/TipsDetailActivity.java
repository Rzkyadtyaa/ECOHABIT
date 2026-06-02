package com.rizky.ecohabit;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TipsDetailActivity extends AppCompatActivity {

    TextView txtEmoji, txtTitle;
    TextView txtDescription, txtBenefit, txtHow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tips_detail);

        txtEmoji = findViewById(R.id.txtEmoji);
        txtTitle = findViewById(R.id.txtTitle);
        txtDescription = findViewById(R.id.txtDescription);
        txtBenefit = findViewById(R.id.txtBenefit);
        txtHow = findViewById(R.id.txtHow);

        String title =
                getIntent().getStringExtra("title");

        String emoji =
                getIntent().getStringExtra("emoji");

        txtTitle.setText(title);
        txtEmoji.setText(emoji);

        // ================= HEMAT AIR =================

        if(title.equals("Hemat Air")) {

            txtDescription.setText(
                    "Air bersih merupakan sumber daya penting bagi kehidupan. Menghemat air membantu menjaga persediaan air bersih di masa depan."
            );

            txtBenefit.setText(
                    "• Mengurangi pemborosan air\n\n" +
                            "• Menjaga lingkungan\n\n" +
                            "• Menghemat tagihan air"
            );

            txtHow.setText(
                    "• Matikan keran saat tidak digunakan\n\n" +
                            "• Gunakan air secukupnya\n\n" +
                            "• Perbaiki kebocoran air"
            );
        }

        // ================= PLASTIK =================

        else if(title.equals("Kurangi Plastik")) {

            txtDescription.setText(
                    "Sampah plastik membutuhkan waktu sangat lama untuk terurai dan mencemari lingkungan."
            );

            txtBenefit.setText(
                    "• Mengurangi pencemaran lingkungan\n\n" +
                            "• Menjaga laut tetap bersih\n\n" +
                            "• Melindungi hewan"
            );

            txtHow.setText(
                    "• Gunakan tumbler\n\n" +
                            "• Hindari sedotan plastik\n\n" +
                            "• Gunakan tas reusable"
            );
        }

        // ================= SEPEDA =================

        else if(title.equals("Gunakan Sepeda")) {

            txtDescription.setText(
                    "Menggunakan sepeda membantu mengurangi emisi karbon dan membuat tubuh lebih sehat."
            );

            txtBenefit.setText(
                    "• Mengurangi polusi udara\n\n" +
                            "• Menyehatkan tubuh\n\n" +
                            "• Menghemat bahan bakar"
            );

            txtHow.setText(
                    "• Gunakan sepeda untuk jarak dekat\n\n" +
                            "• Ajak teman bersepeda\n\n" +
                            "• Gunakan helm demi keamanan"
            );
        }

        // ================= LISTRIK =================

        else if(title.equals("Hemat Listrik")) {

            txtDescription.setText(
                    "Menghemat listrik membantu mengurangi penggunaan energi berlebih dan menjaga lingkungan."
            );

            txtBenefit.setText(
                    "• Mengurangi tagihan listrik\n\n" +
                            "• Menghemat energi\n\n" +
                            "• Mengurangi emisi karbon"
            );

            txtHow.setText(
                    "• Matikan lampu saat tidak dipakai\n\n" +
                            "• Cabut charger\n\n" +
                            "• Gunakan lampu LED"
            );
        }

        // ================= POHON =================

        else if(title.equals("Menanam Pohon")) {

            txtDescription.setText(
                    "Pohon menghasilkan oksigen dan membantu menyerap karbon dioksida dari udara."
            );

            txtBenefit.setText(
                    "• Udara lebih bersih\n\n" +
                            "• Mengurangi panas\n\n" +
                            "• Lingkungan lebih hijau"
            );

            txtHow.setText(
                    "• Tanam pohon di rumah\n\n" +
                            "• Rawat tanaman rutin\n\n" +
                            "• Ikut kegiatan penghijauan"
            );
        }

        // ================= FOOD WASTE =================

        else if(title.equals("Kurangi Food Waste")) {

            txtDescription.setText(
                    "Membuang makanan berlebihan menyebabkan pemborosan sumber daya dan meningkatkan sampah."
            );

            txtBenefit.setText(
                    "• Mengurangi sampah makanan\n\n" +
                            "• Menghemat pengeluaran\n\n" +
                            "• Menghargai makanan"
            );

            txtHow.setText(
                    "• Ambil makanan secukupnya\n\n" +
                            "• Simpan makanan dengan baik\n\n" +
                            "• Habiskan makanan"
            );
        }
    }
}