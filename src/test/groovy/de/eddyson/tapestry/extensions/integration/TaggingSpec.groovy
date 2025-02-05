package de.eddyson.tapestry.extensions.integration

import de.eddyson.tapestry.extensions.integration.pages.TaggingDemo
import de.eddyson.tapestrygeb.JettyGebSpec
import org.openqa.selenium.Keys


class TaggingSpec extends JettyGebSpec{

  def "Add tags with completion"(){
    given:
    to TaggingDemo

    when:
    search << "fo"
    waitFor { $(".select2-results__option", text:"Foo").displayed }
    $(".select2-results__option", text:"Foo").click()
    submit.click()

    then:
    taglist.children().size() == 1
    taglist.children()[0].text()== "Foo"

    when:
    search << "New Tag"
    waitFor {$(".select2-results__option", text:"New Tag").displayed}
    search << Keys.ENTER
    submit.click()

    then:
    taglist.children().size() == 2
    taglist.children().text()== "Foo"
    taglist.children()[1].text()== "New Tag"


  }
}
