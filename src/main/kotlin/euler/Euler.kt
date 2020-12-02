package euler

import euler.level1.*
import euler.level10.*
import euler.level11.*
import euler.level2.*
import euler.level3.*
import euler.level4.*
import euler.level5.*
import euler.level8.*
import java.math.*
import kotlin.reflect.*
import euler.numberic.Number as AsciiNumber



fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Executing all problems...")

        val results = problems.values.map { it.execute() }

        System.out.printf("Problems solved: %d out of %d\tTotal duration: %5.2f seconds%n",
                results.count { it.first == ProblemResult.Correct },
                results.size,
                results.map { it.second }.sum() / 1e9)
    } else {
        try {
            val problemNumber = if (args[0][0] == 'd') {
                enableDebugMode()
                args[0].substring(1).toInt()
            } else {
                args[0].toInt()
            }

            val problem = problems[problemNumber]

            if (problem == null) {
                println("Problem $problemNumber not found")
            } else {
                problem.execute()
            }
        } catch (ex: NumberFormatException) {
            println("Please give the number of the problem that you want to solve!")
        }
    }
}

enum class ProblemResult {
    NotFound,
    Unchecked,
    Correct,
    Incorrect,
}

data class ProblemDesc<T : Number>(
        val index: Int,
        val function: KFunction<T?>?,
        val solution: T? = null
) {
    fun execute(): Pair<ProblemResult, Long> {
        if (function == null) {
            return ProblemResult.NotFound to 0L
        }

        val start = System.nanoTime()
        val result = function.call()
        val time = System.nanoTime() - start

        return if (result == null) {
            System.out.printf("          Result not found for Problem %3d Calculated in %5.2f seconds%n",
                    index,
                    time / 1e9)

            ProblemResult.NotFound to time
        } else {
            val checked = when (solution) {
                null -> ProblemResult.Unchecked
                result -> ProblemResult.Correct
                else -> ProblemResult.Incorrect
            }

            System.out.printf("%9s result for Problem %3d: %20s Calculated in %5.3f seconds%n",
                    checked,
                    index,
                    result.toString(),
                    time / 1e9)

            checked to time
        }
    }
}

val problems = listOf(
        ProblemDesc(1, ::problem1, 233_168),
        ProblemDesc(2, ::problem2, 4_613_732),
        ProblemDesc(3, ::problem3, 6_857),
        ProblemDesc(4, ::problem4, 906_609),
        ProblemDesc(5, ::problem5, 232_792_560),
        ProblemDesc(6, ::problem6, 25_164_150),
        ProblemDesc(7, ::problem7, 104_743),
        ProblemDesc(8, ::problem8, 23_514_624_000L),
        ProblemDesc(9, ::problem9, 31_875_000),
        ProblemDesc(10, ::problem10, 142_913_828_922L),
        ProblemDesc(11, ::problem11, 70_600_674),
        ProblemDesc(12, ::problem12, 76_576_500),
        ProblemDesc(13, ::problem13, 5_537_376_230),
        ProblemDesc(14, ::problem14, 837_799),
        ProblemDesc(15, ::problem15, 137_846_528_820L),
        ProblemDesc(16, ::problem16, 1_366),
        ProblemDesc(17, ::problem17, 21_124),
        ProblemDesc(18, Problem018()::solve, 1_074),
        ProblemDesc(19, ::problem19, 171),
        ProblemDesc(20, ::problem20, 648),
        ProblemDesc(21, ::problem21, 31_626),
        ProblemDesc(22, Problem022()::solve, 871_198_282),
        ProblemDesc(23, Problem023()::solve, 4_179_871),
        ProblemDesc(24, Problem024()::solve, 2_783_915_460),
        ProblemDesc(25, Problem025()::solve, 4_782),
        ProblemDesc(26, Problem026()::solve, 983),
        ProblemDesc(27, Problem027()::solve, -59_231),
        ProblemDesc(28, Problem028()::solve, 669_171_001),
        ProblemDesc(29, Problem029()::solve, 9_183),
        ProblemDesc(30, Problem030()::solve, 443_839),
        ProblemDesc(31, Problem031()::solve, 73_682),
        ProblemDesc(32, Problem032()::solve, 45_228),
        ProblemDesc(33, Problem033()::solve, 100),
        ProblemDesc(34, Problem034()::solve, 40_730),
        ProblemDesc(35, Problem035()::solve, 55),
        ProblemDesc(36, Problem036()::solve, 872_187),
        ProblemDesc(37, Problem037()::solve, 748_317),
        ProblemDesc(38, Problem038()::solve, AsciiNumber.valueOf(932_718_654)),
        ProblemDesc(39, Problem039()::solve, 840),
        ProblemDesc(40, Problem040()::solve, 210),
        ProblemDesc(41, Problem041()::solve, 7_652_413),
        ProblemDesc(42, Problem042()::solve, 162),
        ProblemDesc(43, Problem043()::solve, 16_695_334_890),
        ProblemDesc(44, Problem044()::solve, 5_482_660),
        ProblemDesc(45, Problem045()::solve, 1_533_776_805),
        ProblemDesc(46, Problem046()::solve, 5_777),
        ProblemDesc(47, Problem047()::solve, 134_043),
        ProblemDesc(48, Problem048()::solve, AsciiNumber.valueOf(9_110_846_700)),
        ProblemDesc(49, Problem049()::solve, AsciiNumber.valueOf(296_962_999_629)),
        ProblemDesc(50, Problem050()::solve, 997_651),
        ProblemDesc(51, Problem051()::solve, 121_313),
        ProblemDesc(52, Problem052()::solve, 142_857),
        ProblemDesc(53, Problem053()::solve, 4_075),
        ProblemDesc(54, Problem054()::solve, 376),
        ProblemDesc(55, Problem055()::solve, 249),
        ProblemDesc(56, Problem056()::solve, 972),
        ProblemDesc(57, Problem057()::solve, 153),
        ProblemDesc(58, Problem058()::solve, 26_241),
        ProblemDesc(59, Problem059()::solve, 107_359),
        ProblemDesc(60, Problem060()::solve, 26_033),
        ProblemDesc(61, Problem061()::solve, 28_684),
        ProblemDesc(62, Problem062()::solve, AsciiNumber.valueOf(127_035_954_683)),
        ProblemDesc(63, Problem063()::solve, 49),
        ProblemDesc(64, Problem064()::solve, 1_322),
        ProblemDesc(65, Problem065()::solve, 272),
        ProblemDesc(66, Problem066()::solve, 661),
        ProblemDesc(67, Problem067()::solve, 7_273),
        ProblemDesc(68, Problem068()::solve, 6_531_031_914_842_725),
        ProblemDesc(69, Problem069()::solve, 510_510),
        ProblemDesc(70, Problem070()::solve, 8_319_823),
        ProblemDesc(71, Problem071()::solve, 428_570),
        ProblemDesc(72, Problem072()::solve, 303_963_552_391),
        ProblemDesc(73, Problem073()::solve, 7_295_372),
        ProblemDesc(74, Problem074()::solve, 402),
        ProblemDesc(75, ::problem75, 161_667),
        ProblemDesc(76, Problem076()::solve, 190_569_291),
        ProblemDesc(77, Problem077()::solve, 71),
        ProblemDesc(78, Problem078()::solve, 55_374),
        ProblemDesc(79, Problem079()::solve, 73_162_890),
        ProblemDesc(80, Problem080()::solve, 40_886),
        ProblemDesc(81, Problem081()::solve, 427_337),
        ProblemDesc(82, Problem082()::solve, 260_324),
        ProblemDesc(83, Problem083()::solve, 425_185),
        ProblemDesc(84, Problem084()::solve, 101_524),
        ProblemDesc(85, Problem085()::solve, 2_772),
        ProblemDesc(86, Problem086()::solve, 1_818),
        ProblemDesc(87, Problem087()::solve, 1_097_343),
        ProblemDesc(88, Problem088()::solve, 7_587_457),
        ProblemDesc(89, Problem089()::solve, 743),
        ProblemDesc(90, Problem090()::solve, 1_217),
        ProblemDesc(91, Problem091()::solve, 14_234),
        ProblemDesc(92, Problem092()::solve, 8_581_146),
        ProblemDesc(93, Problem093()::solve, 1_258),
        ProblemDesc(94, Problem094()::solve, 518_408_346),
        ProblemDesc(95, Problem095()::solve, 14_316),
        ProblemDesc(96, Problem096()::solve, 24_702),
        ProblemDesc(97, Problem097()::solve, BigInteger.valueOf(8_739_992_577)),
        ProblemDesc(98, Problem098()::solve, 18_769),
        ProblemDesc(99, Problem099()::solve, 709),
        ProblemDesc(100, Problem100()::solve, 756_872_327_473),
        ProblemDesc(101, Problem101()::solve, 37_076_114_526),
        ProblemDesc(102, Problem102()::solve, 228),
        ProblemDesc(103, Problem103()::solve, 20_313_839_404_245),
        ProblemDesc(104, Problem104()::solve, 329_468),
        ProblemDesc(105, Problem105()::solve, 73_702),
        ProblemDesc(106, Problem106()::solve, 21_384),
        ProblemDesc(107, Problem107()::solve, 259_679),
        ProblemDesc(108, Problem108()::solve, 180_180),
        ProblemDesc(109, Problem109()::solve, 38_182),
        ProblemDesc(110, Problem110()::solve, 9_350_130_049_860_600),
        ProblemDesc(111, Problem111()::solve, 612_407_567_715),
        ProblemDesc(112, Problem112()::solve, 1_587_000),
        ProblemDesc(113, Problem113()::solve, BigInteger.valueOf(51_161_058_134_250)),
        ProblemDesc(114, Problem114()::solve, 16_475_640_049),
        ProblemDesc(115, Problem115()::solve, 168),
        ProblemDesc(116, Problem116()::solve, 20_492_570_929),
        ProblemDesc(117, Problem117()::solve, 100_808_458_960_497),
        ProblemDesc(118, Problem118()::solve, 44_680),
        ProblemDesc(119, Problem119()::solve, 248_155_780_267_521),
        ProblemDesc(120, Problem120()::solve, 333_082_500),
        ProblemDesc(121, Problem121()::solve, 2_269),
        ProblemDesc(122, Problem122()::solve, 1_582),
        ProblemDesc(123, Problem123()::solve, 21_035),
        ProblemDesc(124, Problem124()::solve, 21_417),
        ProblemDesc(125, Problem125()::solve, 2_906_969_179),
        ProblemDesc(126, Problem126()::solve, 18_522),
        ProblemDesc(127, Problem127()::solve, 18_407_904),
        ProblemDesc(128, Problem128()::solve, 14_516_824_220),
        ProblemDesc(129, Problem129()::solve, 1_000_023),
        ProblemDesc(130, Problem130()::solve, 149_253),
        ProblemDesc(131, Problem131()::solve, 173),
        ProblemDesc(132, Problem132()::solve, 843_296),
        ProblemDesc(133, Problem133()::solve, 453_647_705),
        ProblemDesc(134, Problem134()::solve, BigInteger.valueOf(18_613_426_663_617_118)),
        ProblemDesc(135, Problem135()::solve, 4_989),
        ProblemDesc(136, Problem136()::solve, 2_544_559),
        ProblemDesc(137, Problem137()::solve, 1_120_149_658_760),
        ProblemDesc(138, Problem138()::solve, BigInteger.valueOf(1_118_049_290_473_932)),
        ProblemDesc(139, Problem139()::solve, 10_057_761),
        ProblemDesc(140, Problem140()::solve, 5_673_835_352_990),
        ProblemDesc(141, Problem141()::solve, 878_454_337_159),
        ProblemDesc(142, Problem142()::solve, 1_006_193),
        ProblemDesc(143, ::problem143, 30_758_397),
        ProblemDesc(144, Problem144()::solve, 354),
        ProblemDesc(145, Problem145()::solve, 608_720),
        ProblemDesc(146, ::problem146, 676_333_270L),
        ProblemDesc(160, Problem160()::solve, 16_576),
        ProblemDesc(162, Problem162()::solve, BigInteger.valueOf(0x3D58725572C62302)),
        ProblemDesc(164, Problem164()::solve, 378_158_756_814_587),
        ProblemDesc(165, Problem165()::solve, 2_868_868),
        ProblemDesc(179, Problem179()::solve, 986_262),
        ProblemDesc(187, Problem187()::solve, 17_427_258),
        ProblemDesc(204, Problem204()::solve, 2_944_730),
        ProblemDesc(205, Problem205()::solve, 0.5731441),
        ProblemDesc(206, Problem206()::solve, 1_389_019_170),
        ProblemDesc(357, Problem357()::solve, 1_739_023_853_137),
        ProblemDesc(387, Problem387()::solve, 696_067_597_313_468),
        ProblemDesc(493, Problem493()::solve, 6.818741802),
        ProblemDesc(549, Problem549()::solve, 476_001_479_068_717)
).map { it.index to it }.toMap()
