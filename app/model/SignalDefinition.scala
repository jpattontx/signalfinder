package model

import com.sun.tools.javac.code.Attribute.Compound

/**
  *
  */
trait SignalDefinition {
  def getTermsToMatch:Set[String]
  def isValid(matchedTerms:Set[String]):Boolean
}

case class SimpleSignalDefinition (val terms:Set[String]) extends SignalDefinition {

  override def getTermsToMatch: Set[String] = terms

  override def isValid(matchedTerms: Set[String]): Boolean = {
    terms.subsetOf(matchedTerms)
  }
}


abstract class CompoundSignalDefinition (signalDefs:Seq[SignalDefinition]) extends SignalDefinition {
  override def getTermsToMatch: Set[String] = signalDefs.flatMap(_.getTermsToMatch).toSet
}

case class AndSignalDefinition (signalDefs:Seq[SignalDefinition]) extends CompoundSignalDefinition (signalDefs:Seq[SignalDefinition]) {

  override def isValid(matchedTerms: Set[String]): Boolean = {
    var valid = true
    for (sd <- signalDefs) {
      if (valid && !sd.isValid(matchedTerms)) {
        valid = false
      }
    }
    valid
  }
}

case class OrSignalDefinition (signalDefs:Seq[SignalDefinition]) extends CompoundSignalDefinition (signalDefs:Seq[SignalDefinition]) {

  override def isValid(matchedTerms: Set[String]): Boolean = {
    var valid = false
    for (sd <- signalDefs) {
      if (!valid && sd.isValid(matchedTerms)) {
        valid = true
      }
    }
    valid
  }
}

object SignalDefinition {

  val signals:List[SignalDefinition] = List(

  )

  OrSignalDefinition(Seq(SimpleSignalDefinition(Set("artificial intelligence","cuda")),SimpleSignalDefinition(Set("AI","cuda"))))

  ("Artificial Intelligence") OR (AI)


  /*

("Artificial Intelligence" AND cuda) OR (AI AND cuda)
("Artificial Intelligence") OR (AI)
Caffe or "Caffe Software" or "Caffe Deep Learning"
Caffe AND cuda
Deep Learning
Deep Learning AND cuda
IBM Watson AND cuda
Machine Learning
Machine Learning AND cuda
Tensor Flow AND cuda
TensorFlow or Tensor Flow
Theano
theano AND cuda
Apache mesos
Azure Stack
Bare Metal Cloud OR "BareMetal Cloud"
hybrid cloud
kubernetes
VmWare Vcloud Air
VMWare Cloud AND AWS
vmware vrealize
"ethernet" + "HPC"
"MPI" + "HPC"
"MPI" + "parallel"
("Oracle flexible architecture" AND "Silicon Photonics") OR (OFA AND "Silicon Photonics")
("Oracle flexible architecture" AND Ethernet) OR (OFA AND Ethernet)
("Oracle flexible architecture" AND omnipath) OR (OFA AND omnipath)
10gbe OR 40gbe OR 100gbe OR 400gbe
Coarse wavelength division multiplexing OR cwdm
gasnet
GPFS OR "General Parallel File System"
Infiniband
iSCSI Extensions for RDMA
Java Networking OR "Java Network"
Message Passing Interface
NVMe OR "NVM Express"
Omnipath or omni-path
OpenMP
RocksDB OR "Rocks DB"
Silicon Photonics
VCSEL
"distributed cloud"
"edge computing"
"fog computing"
"management network orchestration"
"microservices" + "network"
"orchestration" + "network"
"sd-wan"
5G
NFV OR "Network Function Virtualization"
NFVi
ONAP OR "Open Network Automation Platform"
OpenDaylight OR (ODL AND Linux)
OpenFlow
SDN OR "Software Defined Network" -"Shared data network"
software defined data center OR SDDC
Software defined infrastructure
ucpe
vcpe
VNF
VNF or NFV + "containers"
vswitch





   */

}
