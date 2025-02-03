package com.example.mobile_computing.data.car_app

import com.example.mobile_computing.R

object SampleData {

    val carBrandSample = listOf(
        CarBrand("0", "Toyota", R.drawable.toyota_logo),
        CarBrand("1", "Nissan", R.drawable.nissan_logo),
        CarBrand("2", "Mazda", R.drawable.mazda_logo),
        CarBrand("3", "Honda", R.drawable.honda_logo),
        CarBrand("4", "Subaru", R.drawable.subaru_logo),
        CarBrand("5", "Mitsubishi", R.drawable.mitsubishi_logo)
    )

    val carSample = listOf(
        Car(
            id = "0",
            brand = CarBrand("0", "toyota", R.drawable.toyota_logo),
            model = "Supra MK4",
            year = 1994,
            detail = "Iconic JDM car known for its powerful 2JZ engine and timeless design.",
            image = R.drawable.toyota_supra_mk4
        ),
        Car(
            id = "1",
            brand = CarBrand("1", "Nissan", R.drawable.nissan_logo),
            model = "Skyline GT-R R34",
            year = 1999,
            detail = "A legendary car in the JDM world, featuring advanced all-wheel-drive and RB26 engine.",
            image = R.drawable.nissan_skyline_r34
        ),
        Car(
            id = "2",
            brand = CarBrand("2", "Mazda", R.drawable.mazda_logo),
            model = "RX-7 FD",
            year = 1993,
            detail = "A rotary-powered masterpiece with sleek styling and lightweight chassis.",
            image = R.drawable.mazda_rx7_fd
        ),
        Car(
            id = "3",
            brand = CarBrand("3", "Honda", R.drawable.honda_logo),
            model = "NSX",
            year = 1990,
            detail = "The original Japanese supercar, co-developed with Ayrton Senna for precision handling.",
            image = R.drawable.honda_nsx
        ),
        Car(
            id = "4",
            brand = CarBrand("4", "Subaru", R.drawable.subaru_logo),
            model = "Impreza WRX STI",
            year = 2004,
            detail = "Rally-bred sedan with turbocharged boxer engine and symmetrical AWD.",
            image = R.drawable.subaru_impreza_wrx_sti
        ),
        Car(
            id = "5",
            brand = CarBrand("5", "Mitsubishi", R.drawable.mitsubishi_logo),
            model = "Lancer Evolution VI",
            year = 1999,
            detail = "A rally-inspired sedan with aggressive styling and advanced turbocharged engine.",
            image = R.drawable.mitsubishi_lancer_evo_vi
        ),
        Car(
            id = "6",
            brand = CarBrand("0", "Toyota", R.drawable.toyota_logo),
            model = "AE86 Trueno",
            year = 1983,
            detail = "A lightweight and nimble car famous for its role in drifting and the anime Initial D.",
            image = R.drawable.toyota_ae86_trueno
        ),
        Car(
            id = "7",
            brand = CarBrand("1", "Nissan", R.drawable.nissan_logo),
            model = "Silvia S15",
            year = 1999,
            detail = "A sleek coupe popular among car enthusiasts for its balance of power and style.",
            image = R.drawable.nissan_silvia_s15
        ),
        Car(
            id = "8",
            brand = CarBrand("2", "Mazda", R.drawable.mazda_logo),
            model = "RX-3",
            year = 1971,
            detail = "A classic rotary-powered coupe known for its vintage charm and racing heritage.",
            image = R.drawable.mazda_rx3
        ),
        Car(
            id = "9",
            brand = CarBrand("3", "Honda", R.drawable.nissan_logo),
            model = "Civic Type R EK9",
            year = 1997,
            detail = "A high-performance version of the Civic with a focus on track capabilities.",
            image = R.drawable.honda_civic_type_r_ek9
        )
    )
}
