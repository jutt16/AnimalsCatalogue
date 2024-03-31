package com.example.animalscatalogue;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView nameTextView;
    private TextView descTextView;
    private LinearLayout imageContainer;
    private HorizontalScrollView horizontalScrollView;
    private int[] imageResources = {R.drawable.capybara, R.drawable.chinchilla, R.drawable.guinapig, R.drawable.wombat, R.drawable.cat, R.drawable.dog};
    private String[] animalNames = {"Capybara", "Chinchilla", "Guinea Pig", "Wombat", "Cat", "Dog"};
    private String[] animalDescriptions = {
            "The capybara is the largest rodent in the world. The capybara inhabits savannas and dense forests, and lives near bodies of water. \nThe capybara or greater capybara is a giant cavy rodent native to South America. It is the largest living rodent and a member of the genus Hydrochoerus. The only other extant member is the lesser capybara.",
            "Chinchillas are crepuscular rodents, slightly larger and more robust than ground squirrels. Chinchillas are either of two species of crepuscular rodents of the parvorder Caviomorpha, and are native to the Andes mountains in South America. \nChinchillas are either of two species of crepuscular rodents of the parvorder Caviomorpha, and are native to the Andes mountains in South America. They live in colonies called \"herds\" at high elevations up to 4,270 m.",
            "The guinea pig, also called the cavy, is a species of rodent belonging to the family Caviidae. The guinea pig or domestic guinea pig, also known as the cavy or domestic cavy, is a species of rodent belonging to the genus Cavia in the family Caviidae. Breeders tend to use the name \"cavy\" for the animal, but \"guinea pig\" is more commonly used in scientific and laboratory contexts.",
            "The wombat is a marsupial native to Australia, which is about 1 m (40 in) in length and weighs between 20 and 35 kg (44 and 77 lb). Wombats are short-legged, muscular quadrupedal marsupials of the family Vombatidae that are native to Australia. \nWombats are short-legged, muscular quadrupedal marsupials of the family Vombatidae that are native to Australia. Living species are about 1 m in length with small, stubby tails and weigh between 20 and 35 kg.",
            "The cat, commonly referred to as the domestic cat or house cat, is the only domesticated species in the family Felidae. Recent advances in archaeology and genetics have shown that the domestication of the cat occurred in the Near East around 7500 BC. \nDomestic cats are characterized by retractable claws, powerful bodies, acute senses, long tails, and specialized teeth adapted for hunting prey. \nDomestic cats are characterized by retractable claws, powerful bodies, acute senses, long tails, and specialized teeth adapted for hunting prey.",
            "The dog is a domesticated descendant of the wolf. Also called the domestic dog, it is derived from extinct gray wolves, and the gray wolf is the dog's closest living relative. The dog was the first species to be domesticated by humans. \nThe term “domestic dog” refers to any of several hundred breeds of dog in the world today."
    };
    private int selectedImageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        nameTextView = findViewById(R.id.name_textview);
        descTextView = findViewById(R.id.desc_textview);
        imageContainer = findViewById(R.id.imageContainer);
        horizontalScrollView = findViewById(R.id.horizontalScrollView);

        populateImages();

        findViewById(R.id.previous_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPreviousImage();
            }
        });

        findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextImage();
            }
        });
    }

    private void populateImages() {
        // Calculate desired width and height for the images
        int imageWidth = getResources().getDimensionPixelSize(R.dimen.image_width);
        int imageHeight = getResources().getDimensionPixelSize(R.dimen.image_height);

        for (int i = 0; i < imageResources.length; i++) {
            ImageView img = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    imageWidth, // Fixed width
                    imageHeight // Fixed height
            );
            layoutParams.setMargins(10, 0, 10, 0);
            img.setLayoutParams(layoutParams);
            img.setImageResource(imageResources[i]);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP); // Set scale type to centerCrop
            img.setTag(i);
            img.setPadding(10,10,10,10);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = (int) v.getTag();
                    selectImage(index);
                }
            });
            imageContainer.addView(img);
        }
    }


    private void showPreviousImage() {
        if (selectedImageIndex > 0) {
            selectedImageIndex--;
            selectImage(selectedImageIndex);
            scrollToSelectedImage(selectedImageIndex);
        }
    }

    private void showNextImage() {
        if (selectedImageIndex < imageResources.length - 1) {
            selectedImageIndex++;
            selectImage(selectedImageIndex);
            scrollToSelectedImage(selectedImageIndex);
        }
    }

    private void scrollToSelectedImage(int index) {
        // Get the selected image view
        ImageView selectedImg = (ImageView) imageContainer.getChildAt(index);

        // Calculate the scroll position to center the selected image
        int scrollX = selectedImg.getLeft() - (horizontalScrollView.getWidth() - selectedImg.getWidth()) / 2;
        horizontalScrollView.smoothScrollTo(scrollX, 0);
    }


    private void selectImage(int index) {
        ImageView img = (ImageView) imageContainer.getChildAt(index);
        img.setBackgroundResource(R.drawable.image_outline); // Outline the selected image
        for (int i = 0; i < imageResources.length; i++) {
            if (i != index) {
                ImageView otherImg = (ImageView) imageContainer.getChildAt(i);
                otherImg.setBackground(null); // Remove outline from other images
            }
        }
        imageView.setImageResource(imageResources[index]);
        nameTextView.setText(animalNames[index]);
        descTextView.setText(animalDescriptions[index]);
        selectedImageIndex = index;
    }
}
