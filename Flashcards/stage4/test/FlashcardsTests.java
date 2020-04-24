import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testcase.TestCase;
import flashcards.Main;
import org.hyperskill.hstest.testing.TestedProgram;

import static org.hyperskill.hstest.testing.expect.Expectation.expect;

import java.util.Arrays;
import java.util.List;

public class FlashcardsTests extends StageTest<String> {
    @Override
    public List<TestCase<String>> generate() {
        return Arrays.asList(
                new TestCase<String>()
                        .setDynamicTesting(() -> {
                            TestedProgram main = new TestedProgram(Main.class);
                            main.start();
                            main.execute("2");
                            main.execute("black");
                            main.execute("white");
                            String output = main.execute("black").toLowerCase();
                            expect(output).toContainAtLeast(1).words();
                            if (!(output.contains("\"black\""))) {
                                return CheckResult.wrong("Expected a warning that the term already exists");
                            }
                            main.execute("red");
                            String output1 = main.execute("white");
                            expect(output1).toContainAtLeast(1).words();
                            if (!(output1.contains("\"white\""))) {
                                return CheckResult.wrong("Expected a warning that the definition already exists");
                            }
                            String output2 = main.execute("green");
                            expect(output2).toContainAtLeast(1).words();
                            if (!(output2.contains("\"black\""))) return CheckResult.wrong("The game was not started");

                            String output3 = main.execute("white");
                            if (!(output3.contains("\"red\"")))
                                return CheckResult.wrong("Wrong definition is expected");

                            main.execute("green");
                            if (!(main.isFinished())) {
                                return CheckResult.wrong("Wrong definition is expected");
                            }
                            return CheckResult.correct();
                        }),
                new TestCase<String>()
                        .setDynamicTesting(() -> {
                            TestedProgram main = new TestedProgram(Main.class);
                            main.start();
                            main.execute("2");
                            main.execute("a brother of one's parent");
                            main.execute("uncle");
                            main.execute("a part of the body where the foot and the leg meet");
                            String output = main.execute("ankle");
                            expect(output).toContainAtLeast(1).words();
                            if (!(output.contains("\"a brother of one's parent\""))) {
                                return CheckResult.wrong("The game was not started");
                            }
                            String output1 = main.execute("ankle");
                            expect(output1).toContainAtLeast(1).words();
                            output1 = output1.split("\n")[0];
                            if (!(output1.contains("\"uncle\""))) {
                                return CheckResult.wrong("Wrong definition is accepted");
                            } else if (!(output1.contains("\"a part of the body where the foot and the leg meet\""))) {
                                return CheckResult.wrong("The original term was not printed");
                            }
                            String output2 = main.execute("???");
                            expect(output2).toContainAtLeast(1).words();
                            if (!(output2.contains("\"ankle\""))) {
                                return CheckResult.wrong("Wrong definition was accepted");
                            }
                            if (!(main.isFinished())) {
                                return CheckResult.wrong("The program was not stopped");
                            }
                            return CheckResult.correct();
                        })
        );
    }
}
