package com.example.batcomputer.dc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.batcomputer.R
import com.example.batcomputer.adapter.MyAdapter
import com.example.batcomputer.data.SuperHero
import com.example.batcomputer.databinding.ActivityDcheroBinding

class DCHeroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDcheroBinding
    private lateinit var heroList: ArrayList<SuperHero>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDcheroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Justice League"

        val superEssay = "Superman was born on the fictional planet Krypton and was named Kal-El. As a baby, his parents sent him to Earth in a small spaceship moments before Krypton was destroyed in a natural cataclysm. His ship landed in the American countryside, near the fictional town of Smallville. He was found and adopted by farmers Jonathan and Martha Kent, who named him Clark Kent. Clark developed various superhuman abilities, such as incredible strength and impervious skin. His adoptive parents advised him to use his abilities for the benefit of humanity, and he decided to fight crime. To protect his personal life, he changes into a colorful costume and uses the alias Superman when fighting crime. Clark resides in the Metropolis,where he works as a journalist for the Daily Planet. Superman's supporting characters include his love interest and fellow journalist Lois Lane, and his archenemy Lex Luthor."
        val batEssay = "Batman is the alias of Bruce Wayne, a wealthy American playboy, philanthropist, and industrialist who resides in Gotham City.Batman's origin story features him swearing vengeance against criminals after witnessing the murder of his parents Thomas and Martha, who were robbed and shot to death infront of an eight-year-old Bruce, a vendetta tempered with the ideal of justice. He trains himself physically and intellectually, crafts a bat-inspired persona, and monitors the Gotham streets at night with allies Alfred Pennyworth, James Gordon, and Catwoman; and foes such as the Penguin,the Riddler,The Joker."
        val wwEssay = " Wonder Woman title has been published by DC Comics almost continuously ever since.[10] In her homeland, the island nation of Themyscira, her official title is Princess Diana of Themyscira. When blending into the society outside of her homeland, she sometimes adopts her civilian identity Diana Prince. Wonder Woman's origin story relates that she was sculpted from clay by her mother Queen Hippolyta and was given a life as an Amazon, along with superhuman powers as gifts by the Greek gods. In 2011, DC changed her background with the retcon that she is the biological daughter of Zeus and Hippolyta, jointly raised by her mother and her aunts Antiope and Menalippe."
        val aquaEssay = "The best-known version of Aquaman, developed in the “Silver Age” of comic books in the 1950s and ’60s, is the son of a lighthouse keeper and an Atlantean outcast. In 1964 he became one of the first superheroes to get married when he wed the undersea queen Mera. His nemeses include the supervillain Black Manta, who murdered one of Aquaman’s sons, and Ocean Master, who is also Aquaman’s half-brother Orm."
        val cyEssay = "Victor Stone is the son of Silas Stone and Elinore Stone, scientists who use him as a test subject for various intelligence enhancement projects. While these treatments are ultimately successful and Victor's IQ subsequently grows to genius levels, he grows to resent his treatment.Victor's situation changes radically when he visits his parents' lab where experiments in inter-dimensional access are done. At that moment of his entry, an aggressive gelatinous creature was accidentally pulled through and Victor's mother is killed by it. It then turned on Victor and he was severely injured by its attack before his father was able to send it back to its native dimension. Although his bitterness remained for some time, Victor eventually calmed down enough to successfully adjust to his implants physically. He found himself rejected by the public because of his implants, including his girlfriend. "
        val flashEssay = "The Flash (Barry Allen) is a superhero. Because he is a speedster, his power consists mainly of superhuman speed. Various other effects are also attributed to his ability to control the slowness of molecular vibrations, including his ability to vibrate at speed to pass through objects. The Flash wears a distinct red and gold costume treated to resist friction and wind resistance, traditionally storing the costume compressed inside a ring. Barry Allen's classic stories introduced the concept of the Multiverse "
        val grEssay = "Green Lantern is the name of several superheroes appearing in American comic books published by DC Comics. They fight evil with the aid of rings that grant them a variety of extraordinary powers, all of which come from imagination, fearlessness, and the electromagnetic spectrum of emotional willpower.[1] The characters are typically depicted as members of the Green Lantern Corps, an intergalactic law enforcement agency."
        val jokerEssay = " Joker is portrayed as a criminal mastermind. Introduced as a psychopath with a warped, sadistic sense of humor, the character became a goofy prankster in the late 1950s in response to regulation by the Comics Code Authority, before returning to his darker roots during the early 1970s. As Batman's nemesis, the Joker has been part of the superhero's defining stories, including the murder of Jason Todd—the second Robin and Batman's ward—and the paralysis of one of Batman's allies,"
        val lexEssay = "Lex Luthor was originally depicted as a narcissistic and egotistical mad scientist from the 1960s to the early 1980s. Since the late 1980s, he has more often been portrayed as the power-mad CEO of LexCorp. He wishes to rid the world of Superman, ostensibly because he views Superman as a threat to humanity, but in reality envies Superman's popularity and influence.[2] Given his high profile as a supervillain, however, he has often come into conflict with Batman and other superheroes in the DC Universe.[3] Lex Luthor is physically an ordinary human and has no natural superpowers, but has above-average intelligence, a genius for inventions and a high command of science and technology. He occasionally wears his Warsuit, a suit of mechanized armor that gives him enhanced strength, flight, advanced weaponry, and other capabilities."

        heroList = ArrayList()
        heroList.add(SuperHero("Superman","Kal-el@Krypton.com","Alien",
            R.drawable.superman,superEssay))
        heroList.add(SuperHero("Batman","IM@Vengeance","Human", R.drawable.batman, batEssay))
        heroList.add(SuperHero("Wonder Woman","diana@themyscira.in","Amazon",
            R.drawable.wonderwoman ,wwEssay))
        heroList.add(SuperHero("Aquaman","arthur@atlant.ean","Atlante", R.drawable.aquaman,aquaEssay))
        heroList.add(SuperHero("Cyborg","Ic@You.ai","Humanoid", R.drawable.cyborg,cyEssay))
        heroList.add(SuperHero("Flash","flash@speed.spark","Human", R.drawable.flash,flashEssay))
        heroList.add(SuperHero("Green Lantern","ring@mogo.com","Human", R.drawable.green,grEssay))
        heroList.add(SuperHero("Joker","supervillian.me","PsychoHuman", R.drawable.joker,jokerEssay))
        heroList.add(SuperHero("Lex Luthor","madman@lexcorp","MadHuman", R.drawable.lex,lexEssay))

        val adapterObj = MyAdapter(heroList)

        binding.rvHeroes.adapter = adapterObj
        binding.rvHeroes.layoutManager = LinearLayoutManager(this@DCHeroActivity)


        adapterObj.onItemClick = {
            val intent = Intent(this@DCHeroActivity, DetailActivity::class.java)
            intent.putExtra("superhero", it)
            startActivity(intent)
        }
    }
}