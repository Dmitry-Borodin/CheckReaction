package com.two_two.checkreaction.ui.scienceproject

import com.two_two.checkreaction.di.DependencyProvider
import com.two_two.checkreaction.domain.science.ScienceTargetGenerator
import com.two_two.checkreaction.domain.science.ScienceTestCallback
import com.two_two.checkreaction.models.science.ScienceIterationData
import com.two_two.checkreaction.models.science.ScienceTestResult

/**
 * @author Dmitry Borodin on 2017-01-29.
 */
class ScienceTestPresenter : ScienceTestContract.Presenter {

    val scienceTest = DependencyProvider.getScienceTest()
    val colourProvider = DependencyProvider.getColourProvider()
    var view: ScienceTestContract.View? = null

    override fun bindView(bindedView: ScienceTestContract.View) {
        this.view = bindedView
        showTargetColour()
        scienceTest.callback = object : ScienceTestCallback {
            override fun showNextScreen(iterationData: ScienceIterationData) {
                showNewIterationData(iterationData)
            }

            override fun testFinished(result: ScienceTestResult) {
                view?.navigateToResults(result)
            }
        }
        scienceTest.start()
    }

    private fun showNewIterationData(iterationData: ScienceIterationData) {
        val firstColour = colourProvider.getColorResource(iterationData.shakedOrder[0])
        val secondColour = colourProvider.getColorResource(iterationData.shakedOrder[1])
        val thirdColour = colourProvider.getColorResource(iterationData.shakedOrder[2])
        val forthColour = colourProvider.getColorResource(iterationData.shakedOrder[3])
        view?.setFirstViewColour(firstColour)
        view?.setSecondViewColour(secondColour)
        view?.setThirdViewColour(thirdColour)
        view?.setForthViewColour(forthColour)
    }

    private fun showTargetColour() {
        val targetColor = ScienceTargetGenerator.chosenColorIndex
        view?.setTargetColour(colourProvider.getColorId(targetColor),
                colourProvider.getColourName(targetColor))
    }

    override fun unBindView() {
        view = null
        scienceTest.callback = null
    }

    override fun onFirstViewClicked() {
        scienceTest.onTapped(0)
    }

    override fun onSecondViewClicked() {
        scienceTest.onTapped(1)
    }

    override fun onThirdViewClicked() {
        scienceTest.onTapped(2)
    }

    override fun onForthViewClicked() {
        scienceTest.onTapped(3)
    }


}